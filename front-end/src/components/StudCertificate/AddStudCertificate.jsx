import React, {useState} from 'react'
import StudCertificateService from "../../services/StudCertificateService";
import {useNavigate} from "react-router-dom";
import MyAlert from "../../layouts/MyAlert";

function AddStudCertificate() {
    const [vietCopy, setVietCopy] = useState(0);
    const [phoneContact, setPhoneContact] = useState('');
    const [email, setEmail] = useState('');
    const [engCopy, setEngCopy] = useState(0);
    const [content, setContent] = useState('');
    const nav = useNavigate();

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    const saveStudCertificate = (e) => {
        e.preventDefault();
        if (phoneContact === undefined || vietCopy === undefined || email === undefined
            || engCopy === undefined || content === undefined)
            showAlert('Vui lòng nhập đầy đủ thông tin', 'danger');
        else if (vietCopy < 0 || engCopy < 0
            || (vietCopy === 0 && engCopy === 0))
            showAlert('Số nhập không hợp lệ', 'danger');
        else {
            const studCertificate = {
                vietCopy,
                engCopy,
                email,
                phoneContact,
                content,
            };

            StudCertificateService.addStudCertificate(studCertificate).then(res => {
                const onlineServiceId = res.data.onlineServiceId;
                nav(`/service/stud-cert/update/${onlineServiceId}`,{
                    state: {
                        'success': "true"
                    }});
            });
        }
    }

    const changeVietCopyHandler = (e) => {
        setVietCopy(parseInt(e.target.value));
    }

    const changePhoneHandler = (e) => {
        setPhoneContact(e.target.value);
    }

    const changeEmailHandler = (e) => {
        setEmail(e.target.value);
    }

    const changeEngCopyHandler = (e) => {
        setEngCopy(parseInt(e.target.value));
    }

    const changeContentHandler = (e) => {
        setContent(e.target.value);
    }

    const cancel = () => { nav(`/home`); }

    return (
        <div>
            <div className = "container">
                {alert && (
                    <MyAlert
                        message={alert.message}
                        color={alert.color}
                    />
                )}
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3">
                        <h3 className="text-center mt-2">Cấp chứng nhận sinh viên</h3>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group">
                                    <label>Bản sao tiếng Việt</label>
                                    <input placeholder="Bản Việt" name="vietCopy" type="number" min="0" className="form-control"
                                           value={vietCopy} onChange={changeVietCopyHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Bản sao tiếng Anh</label>
                                    <input placeholder="Bản Anh" name="engCopy" type="number" min="0" className="form-control"
                                           value={engCopy} onChange={changeEngCopyHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Số điện thoại</label>
                                    <input placeholder="Số điện thoại" name="phoneContact" className="form-control"
                                           value={phoneContact} onChange={changePhoneHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Email</label>
                                    <input placeholder="Địa chỉ email" name="email" className="form-control"
                                           value={email} onChange={changeEmailHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Nội dung</label>
                                    <input placeholder="Nội dung" name="content" className="form-control"
                                           value={content} onChange={changeContentHandler}/>
                                </div>
                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={saveStudCertificate}>Lưu</button>
                                    <button className="btn btn-secondary ms-1" onClick={cancel}>Hủy</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddStudCertificate;