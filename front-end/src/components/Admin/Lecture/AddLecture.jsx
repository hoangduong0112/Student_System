import React, {useEffect, useState} from 'react'
import LectureService from "../../../services/LectureService";
import {useNavigate, useParams} from "react-router-dom";
import '../../../styles/App.css';
import MyAlert from "../../../layouts/MyAlert";

function AddLecture() {
    const nav = useNavigate();
    const [resp, setResp] = useState('');

    const [lectureName, setLectureName] = useState('');
    const [lecturePhone, setLecturePhone] = useState('');

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };
    const saveLecture = async (e) => {
        e.preventDefault();
        if (lectureName === '' || lecturePhone === '')
            showAlert('Vui lòng nhập đầy đủ thông tin', 'warning');
        else {
            const lecture = {
                lectureName: lectureName,
                lecturePhone: lecturePhone,
            };

            try{
                await LectureService.addLecture(lecture)
                showAlert('Thêm giảng viên thành công.');
            }catch (e) {
                showAlert('Có lỗi xảy ra.', 'danger')
            }
        }
    };


    const changeLectureNameHandler = (e) => {
        setLectureName(e.target.value);
    }

    const changeLecturePhoneHandler = (e) => {
        setLecturePhone(e.target.value);
    }

    const cancel = () => { nav(`/admin/lecture/all`); }

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
                        <h3 className="App mt-2">Thêm môn học</h3>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group">
                                    <label>Tên giảng viên</label>
                                    <input placeholder="tên giảng viên..." className="form-control"
                                           value={lectureName} onChange={changeLectureNameHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Số điện thoại liên lạc</label>
                                    <input placeholder="Số điện thoại liên lạc" className="form-control"
                                           value={lecturePhone} onChange={changeLecturePhoneHandler}/>
                                </div>

                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={saveLecture}>Lưu</button>
                                    <button className="btn btn-secondary ms-1" onClick={cancel.bind(this)}>Trở về</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddLecture