import React, {useState} from 'react'
import DiplomaService from "../../services/DiplomaService";
import {useNavigate} from "react-router-dom";
import MyAlert from "../../layouts/MyAlert";

function AddDiploma() {
    const nav = useNavigate();
    const [copy, setCopy] = useState(0);
    const [phoneContact, setPhoneContact] = useState('');
    const [email, setEmail] = useState('');
    const [diplomaYear, setDiplomaYear] = useState(1970);
    const [diplomaCode, setDiplomaCode] = useState('');
    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };
    const saveDiploma = async (e) => {
        e.preventDefault();
        if (copy === null || phoneContact === '' || email === '' || diplomaYear === null || diplomaCode === '')
            showAlert('Vui lòng nhập đầy đủ thông tin', 'warning');
        else if (copy <= 0)
            showAlert('Số lượng phải lớn hơn 0', 'danger');
        else if(diplomaYear < 1970)
            showAlert('Năm tốt nghiệp không hợp lệ', 'danger');
        else {
            const diploma = {
                copy,
                phoneContact,
                email,
                diplomaYear,
                diplomaCode,
            };

            try {
                const res = await DiplomaService.addDiploma(diploma);
                const onlineServiceId = res.data.onlineServiceId;

                nav(`/service/diploma/update/${onlineServiceId}`, {
                    state: {
                        'success': "true"
                    }
                });
            } catch (error) {
                showAlert('Lỗi không xác định', 'danger');
            }
        }
    }

    const changeCopyHandler = (e) => {
        setCopy(e.target.value);
    }

    const changePhoneHandler = (e) => {
        setPhoneContact(e.target.value);
    }

    const changeEmailHandler = (e) => {
        setEmail(e.target.value);
    }

    const changeYearHandler = (e) => {
        setDiplomaYear(e.target.value);
    }

    const changeCodeHandler = (e) => {
        setDiplomaCode(e.target.value);
    }
    
    const cancel = () => { nav(`/service-cate`); }

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
                        <h3 className="text-center mt-2">Cấp bản sao bằng tốt nghiệp</h3>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group">
                                    <label>Số lượng bản sao: </label>
                                    <input placeholder="Copy" name="copy" className="form-control"
                                           value={copy} onChange={changeCopyHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Số điện thoại: </label>
                                    <input placeholder="Phone Contact" name="phoneContact" className="form-control"
                                           value={phoneContact} onChange={changePhoneHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Email: </label>
                                    <input placeholder="Email Address" name="email" className="form-control"
                                           value={email} onChange={changeEmailHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Năm tốt nghiệp: </label>
                                    <input placeholder="Year" name="year" className="form-control"
                                           value={diplomaYear} onChange={changeYearHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Mã bằng: </label>
                                    <input placeholder="Code" name="code" className="form-control"
                                           value={diplomaCode} onChange={changeCodeHandler}/>
                                </div>
                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={saveDiploma}>Lưu</button>
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

export default AddDiploma