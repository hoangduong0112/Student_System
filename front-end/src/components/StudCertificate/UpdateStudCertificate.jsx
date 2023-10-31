import React, { useState, useEffect } from 'react';
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import StudCertificateService from "../../services/StudCertificateService";
import OnlineService from "../../services/OnlineService";
import {Table} from "reactstrap";
import moment from "moment";
import MyAlert from "../../layouts/MyAlert";

function UpdateStudCertificate() {
    const { id } = useParams();
    const loc = useLocation();
    const nav = useNavigate();

    const [alert, setAlert] = useState(null);
    const [service, setService] = useState({});
    const [lever, setLever] = useState(false);

    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    const { success } = loc.state || false;

    const [studCertificateId, setStudCertificateId] = useState(0);
    const [vietCopyInput, setVietCopyInput] = useState( '');
    const [engCopyInput, setEngCopyInput] = useState('');
    const [emailInput, setEmailInput] = useState( '');
    const [phoneContactInput, setPhoneContactInput] = useState('');
    const [contentInput, setContentInput] = useState( '');


    useEffect(() => {
        if (success) {
            showAlert('Gửi yêu cầu thành công');
        }
    }, [success]);

    useEffect(() => {
        StudCertificateService.getStudCertificate(id).then((res) => {
            let studCertificate = res.data;
            setStudCertificateId(studCertificate.id)
            setVietCopyInput(studCertificate.vietCopy);
            setPhoneContactInput(studCertificate.phoneContact);
            setEmailInput(studCertificate.email);
            setEngCopyInput(studCertificate.engCopy);
            setContentInput(studCertificate.content);
        })
    }, [id]);

    const updateStudCertificate = (e) => {
        e.preventDefault();
        if (phoneContactInput === '' || vietCopyInput === null || emailInput === ''
                || engCopyInput === null || contentInput === '')
            showAlert('Vui lòng nhập đầy đủ thông tin', 'danger');
        else if (vietCopyInput < 0 || engCopyInput < 0
                || (vietCopyInput === '0' && engCopyInput === '0'))
            showAlert('Số không hợp lệ', 'danger');
        else {
            const studCertificate = {
                vietCopy: vietCopyInput,
                engCopy: engCopyInput,
                email: emailInput,
                phoneContact: phoneContactInput,
                content: contentInput,
        };
            StudCertificateService.updateStudCertificate(studCertificate, studCertificateId).then(() => {
                setLever(!lever)
                showAlert('Chỉnh sửa thành công')
            });
        }
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
    }, [lever]);

    const changeVietCopyHandler = (e) => {
        setVietCopyInput(e.target.value);
    }

    const changePhoneHandler = (e) => {
        setPhoneContactInput(e.target.value);
    }

    const changeEmailHandler = (e) => {
        setEmailInput(e.target.value);
    }

    const changeEngCopyHandler = (e) => {
        setEngCopyInput(e.target.value);
    }

    const changeContentHandler = (e) => {
        setContentInput(e.target.value);
    }

    const cancel = () => { nav(`/home`); }

    return (
        <div>
            <div className = "container">
                <div className="row">
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
                </div>
                <div className="row">
                    <div className = "card col-md-6 offset-md-4">
                        <h3 className="text-center mt-2">Chỉnh sửa chứng nhận sinh viên</h3>
                        {alert && (
                            <MyAlert
                                message={alert.message}
                                color={alert.color}
                            />
                        )}
                        <div className = "card-body">
                            <form>
                                <div className = "form-group">
                                    <label>Bản sao tiếng Việt: </label>
                                    <input placeholder="Bản Việt" name="vietCopy" className="form-control"
                                           value={vietCopyInput} onChange={changeVietCopyHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Bản sao tiếng Anh: </label>
                                    <input placeholder="Bản Anh" name="engCopy" className="form-control"
                                           value={engCopyInput} onChange={changeEngCopyHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Số điện thoại: </label>
                                    <input placeholder="Số điện thoại" name="phoneContact" className="form-control"
                                           value={phoneContactInput} onChange={changePhoneHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Email: </label>
                                    <input placeholder="Địa chỉ email" name="email" className="form-control"
                                           value={emailInput} onChange={changeEmailHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Nội dung: </label>
                                    <input placeholder="Nội dung" name="content" className="form-control"
                                           value={contentInput} onChange={changeContentHandler}/>
                                </div>
                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={updateStudCertificate}>Lưu</button>
                                    <button className="btn btn-secondary ms-1" onClick={cancel.bind(this)}>Hủy</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default UpdateStudCertificate