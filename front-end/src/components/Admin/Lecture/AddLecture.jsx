import React, {useEffect, useState} from 'react'
import LectureService from "../../../services/LectureService";
import {useNavigate, useParams} from "react-router-dom";
import '../../../styles/App.css';
import {Alert} from "reactstrap";

function AddLecture() {
    const nav = useNavigate();
    const [resp, setResp] = useState('');

    const [lectureName, setLectureName] = useState('');
    const [lecturePhone, setLecturePhone] = useState('');

    const saveLecture = (e) => {
        e.preventDefault();
        if (lectureName === '' || lecturePhone === '')
            setResp('Vui lòng nhập đầy đủ thông tin');
        else {
            const lecture = {
                lectureName: lectureName,
                lecturePhone: lecturePhone,
            };

            LectureService.addLecture(lecture).then(() => {
                setResp('Thêm giảng viên thành công.');
            });
        }
    };

    const alert = () => {
        if (resp.includes('thành công'))
            return (
                <Alert color="success" className="fixed-bottom"
                       style={{marginBottom:'5rem', marginLeft:'25%', marginRight:'25%'}}
                       onMouseEnter={() => setResp('')}>{resp}
                </Alert>
            )
        else if (resp)
            return (
                <Alert color="danger" className="fixed-bottom"
                       style={{marginBottom:'5rem', marginLeft:'25%', marginRight:'25%'}}
                       onMouseEnter={() => setResp('')}>{resp}
                </Alert>
            )
    }

    const changeLectureNameHandler = (e) => {
        setLectureName(e.target.value);
        setResp('');
    }

    const changeLecturePhoneHandler = (e) => {
        setLecturePhone(e.target.value);
        setResp('');
    }

    const cancel = () => { nav(`/admin/lecture/all`); }

    return (
        <div>
            <div className = "container">
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
                                    <button className="btn btn-secondary ms-1" onClick={cancel.bind(this)}>Hủy</button>
                                </div>
                            </form>
                        </div>
                        {alert()}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddLecture