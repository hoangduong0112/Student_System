import React, {useEffect, useState, useRef} from 'react'
import UnlockStudService from "../../services/UnlockStudService";
import {useLocation, useNavigate, useParams} from "react-router-dom";
import {Button, Card, CardBody, Container, Form, FormGroup, Input, Label, Row, Table} from "reactstrap";
import MyAlert from "../../layouts/MyAlert";
import moment from "moment";
import OnlineService from "../../services/OnlineService";

function UpdateUnlockStud() {
    const { id } = useParams();
    const loc = useLocation();
    const nav = useNavigate();
    const [alert, setAlert] = useState(null);
    const [service, setService] = useState({});
    const [lever, setLever] = useState(false);
    const [preview, setPreview] = useState(null);
    const [loading, setLoading] = useState(false);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    const { success } = loc.state || false;

    const [unlockId, setUnlockId] = useState(0);
    const [image, setImage] = useState('');
    const [content, setContent] = useState( '');

    useEffect(() => {
        if (success) {
            showAlert('Gửi yêu cầu thành công');
        }
    }, []);

    const uploadImage = async () => {
        setLoading(true);
        const data = new FormData();
        data.append("file", image);
        data.append(
            "upload_preset",
            'asrj3wth'
        );
        data.append("cloud_name", 'dmfr3gngl');
        try {
            const response = await fetch(
                `https://api.cloudinary.com/v1_1/dmfr3gngl/image/upload`,
                {
                    method: "POST",
                    body: data,
                }
            );
            const res = await response.json();
            setLoading(false);
            return res.url;
        } catch (error) {
            setLoading(false);
        }
    }
    useEffect(() => {
        const getUnlockById = async () => {
            try{
                let unlockStud = await UnlockStudService.getUnlockStud(id)
                setUnlockId(unlockStud.data.id)
                setPreview(unlockStud.data.image);
                setContent(unlockStud.data.content);
            }catch(e){
                showAlert('Có lổi xảy ra', 'danger');
            }
        }
        getUnlockById()
    }, [id]);

    const saveUnlockStud = async (e) => {
        e.preventDefault();
        if (image === '' || content === '')
            showAlert('Vui lòng nhập đầy đủ thông tin', 'warning');
        else {
            const unlockStud = {
                image: await uploadImage(),
                content: content,
            };
            try{
                const res = await UnlockStudService.updateUnlockStud(unlockStud, unlockId)
                setLever(!lever)
                showAlert('Chỉnh sửa thành công')
            }catch (error) {
                if(error.response.status === 404)
                    showAlert('Không tìm thấy yêu cầu', 'danger')
                else if(error.response.status === 403)
                    showAlert('Bạn không có quyền làm điều này', 'danger')
                else if(error.response.status === 409)
                    showAlert('Yêu cầu đã được xử lý hoặc hủy', 'danger')
                else
                    showAlert('Lỗi hệ thống', 'danger')
            }


        }
    };

    const changeImageHandler = (e) => {
        const file = e.target.files[0];
        setImage(file);
        const reader = new FileReader();
        reader.readAsDataURL(file);

        reader.onload = () => {
            setPreview(reader.result);
        };
    }
    useEffect(() => {

        const getOnlineService = async () => {
            try {
                const res = await OnlineService.getRequestById(id);
                setService(res.data)
            } catch (error) {
                showAlert('Lỗi khi lấy thông tin yêu cầu', 'danger');
            }
        };

        getOnlineService();
    }, []);

    const changeContentHandler = (e) => {
        setContent(e.target.value);
    }

    const cancel = () => { nav(`/home`); }

    return (
        <Container fluid>
            {alert && (
                <MyAlert
                    message={alert.message}
                    color={alert.color}
                />
            )}
            <Row>
                <Table className="mt-3 table table-striped table-bordered">
                    <thead className="text-center"><tr>
                        <th>Giá tiền</th>
                        <th>Ngày yêu cầu</th>
                        <th>Trạng thái</th>
                        <th>Tình trạng</th>
                    </tr></thead>
                    <tbody>
                    <tr key={service.id}>
                        <td>{service.price}</td>
                        <td>{moment(service.createdDate, "YYYY-MM-DD").format("DD-MM-YYYY")}</td>
                        <td>{service.serviceCateName}</td>
                        <td>{service.status}</td>
                    </tr>
                    </tbody>
                </Table>
            </Row>
            <Row className="mt-3">
                <Card className = "col-md-6 offset-md-3">
                    <Row className="justify-content-center pb-2 mt-2 border-bottom h3">Đăng ký mở khóa sinh viên</Row>
                    <CardBody>
                        <Form>
                            <FormGroup>
                                <Label>Ảnh xác thực:</Label>
                                <Input type="file" className="form-control-file" accept="image/*"
                                       onChange={changeImageHandler} multiple={false}   />
                            </FormGroup>
                            <Card className="flex justify-center items-center mt-5 mx-3 max-w-xs">
                                {preview && <img src={preview} alt="preview" className="w-full" />}
                            </Card>
                            <FormGroup>
                                <Label>Nội dung</Label>
                                <Input placeholder="Nội dung" name="content" className="form-control"
                                       value={content} onChange={changeContentHandler}/>
                            </FormGroup>
                            <div className="text-end mt-2">
                                <Button color="primary" className="m-1" onClick={saveUnlockStud}>Lưu</Button>
                                <Button color="secondary" className="m-1" onClick={cancel}>Hủy</Button>
                            </div>
                        </Form>
                    </CardBody>
                </Card>
            </Row>
        </Container>
    )
}

export default UpdateUnlockStud;
