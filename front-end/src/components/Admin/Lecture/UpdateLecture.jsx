import React, { useState, useEffect } from 'react';
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import LectureService from "../../../services/LectureService";
import MyAlert from "../../../layouts/MyAlert";

function UpdateLecture() {
    const { id } = useParams();
    const loc = useLocation();
    const nav = useNavigate();
    const [err, setErr] = useState('');
    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    const [lectureName, setLectureName] = useState('');
    const [lecturePhone, setLecturePhone] = useState( 0);


    useEffect(() => {
        const getLectureById = async () => {
            try {
                const res = await LectureService.getLecture(id);
                let lecture = res.data;
                setLectureName(lecture.lectureName);
                setLecturePhone(lecture.lecturePhone);
            } catch (error) {
                showAlert('Có lỗi xảy ra', 'danger');
            }
        }
        getLectureById()
    }, []);

    const updateLecture = async (e) => {
        e.preventDefault();
        if (lectureName === '' || lecturePhone === null)
            showAlert('Vui lòng nhập đầy đủ thông tin', 'warning');
        else {
            const lecture = {
                lectureName: lectureName,
                lecturePhone: lecturePhone,
            };

            try {
                const res = await LectureService.updateLecture(id, lecture)
                showAlert('Chỉnh sửa thành công');
            } catch (error) {
                if(error.response.status === 404)
                    showAlert(`Không tim thấy môn học ${error.response}`, 'danger');
                else
                    showAlert(`Có lỗi xảy ra`, 'danger');
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
                        <h3 className="App mt-2">Chỉnh sửa giảng viên</h3>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group">
                                    <label>Tên giảng viên: </label>
                                    <input placeholder="tên giảng viên..." name="name" className="form-control"
                                           value={lectureName} onChange={changeLectureNameHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Số điện thoại liên lạc: </label>
                                    <input placeholder="số điện thoại..." name="credits" className="form-control"
                                           value={lecturePhone} onChange={changeLecturePhoneHandler}/>
                                </div>
                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={updateLecture}>Lưu</button>
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

export default UpdateLecture;
