import React, {useEffect, useState} from 'react'
import UnlockStudService from "../../services/UnlockStudService";
import {useLocation, useNavigate} from "react-router-dom";
import {Button, Card, CardBody, Container, Form, FormGroup, Input, Label, Row} from "reactstrap";
import MyAlert from "../../layouts/MyAlert";

function AddUnlockStud() {
    const [image, setImage] = useState(null);
    const [loading, setLoading] = useState(false);
    const [url, setUrl] = useState('');
    const [content, setContent] = useState('');
    const loc = useLocation();
    const nav = useNavigate();
    const [alert, setAlert] = useState(null);
    const [preview, setPreview] = useState(null);

    const { success } = loc.state || false;
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };


    const uploadImage = async () => {
        setLoading(true);
        const data = new FormData();
        data.append("file", image);
        data.append(
            "upload_preset",
            'asrj3wth'
        );

        //api gửi ảnh tới cloudinary -> cloudinary trả về url ảnh
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


    const saveUnlockStud = async (e) => {
        e.preventDefault();
        if (content === '')
            showAlert('Vui lòng nhập đầy đủ thông tin', 'danger');
        else {
            const im = await uploadImage()
            console.log(url)
            const unlockStud = {
                content: content,
                image: im
            };
            try {
                const res = await  UnlockStudService.addUnlockStud(unlockStud);
                const onlineServiceId = res.data.onlineServiceId;

                nav(`/service/unlock-stud/update/${onlineServiceId}`, {
                    state: {
                        'success': "true"
                    }});
            } catch (error) {
                showAlert('Lỗi không xác định', 'danger');
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
    };

    const changeContentHandler = (e) => {
        setContent(e.target.value);
    }

    const cancel = () => { nav(`/home`); }

    return (
        <div>
            {alert && (
                <MyAlert
                    message={alert.message}
                    color={alert.color}
                />
            )}
            <Container fluid>
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
        </div>
    )
}

export default AddUnlockStud;
