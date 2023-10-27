import React, { useState, useEffect } from 'react';
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import LectureService from "../../../services/LectureService";

function UpdateLecture() {
    const { id } = useParams();
    const loc = useLocation();
    const nav = useNavigate();
    const [err, setErr] = useState('');

    const { lectureName, lecturePhone } = loc.state || {};
    const [lectureNameInput, setLectureNameInput] = useState(lectureName || '');
    const [lecturePhoneInput, setLecturePhoneInput] = useState(lecturePhone || 0);


    useEffect(() => {
        LectureService.getLecture(id).then((res) => {
            let lecture = res.data;
            setLectureNameInput(lecture.lectureName);
            setLecturePhoneInput(lecture.lecturePhone);
        })
    }, [id]);

    const updateLecture = (e) => {
        e.preventDefault();
        if (lectureNameInput === '' || lecturePhoneInput === null)
            setErr('Vui lòng nhập đầy đủ thông tin');
        else {
            const lecture = {
                lectureName: lectureNameInput,
                lecturePhone: lecturePhoneInput,
            };

            LectureService.updateLecture(id, lecture).then(() => {
                nav(`/admin/lecture/all`);
            });
        }
    };

    const changeLectureNameHandler = (e) => {
        setLectureNameInput(e.target.value);
        setErr('');
    }

    const changeLecturePhoneHandler = (e) => {
        setLecturePhoneInput(e.target.value);
        setErr('');
    }

    const cancel = () => { nav(`/admin/lecture/all`); }

    return (
        <div>
            <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3">
                        <h3 className="App mt-2">Chỉnh sửa giảng viên</h3>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group">
                                    <label>Tên giảng viên: </label>
                                    <input placeholder="tên giảng viên..." name="name" className="form-control"
                                           value={lectureNameInput} onChange={changeLectureNameHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Số điện thoại liên lạc: </label>
                                    <input placeholder="số điện thoại..." name="credits" className="form-control"
                                           value={lecturePhoneInput} onChange={changeLecturePhoneHandler}/>
                                </div>
                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={updateLecture}>Lưu</button>
                                    <button className="btn btn-secondary ms-1" onClick={cancel.bind(this)}>Hủy</button>
                                </div>
                            </form>
                        </div>
                        {err && <div className="alert alert-danger">{err}</div>}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default UpdateLecture;
