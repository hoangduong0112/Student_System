import React, {useEffect, useState} from 'react'
import CourseService from "../../../services/CourseService";
import {useNavigate, useParams} from "react-router-dom";
import '../../../styles/App.css';
import {Alert} from "reactstrap";
import MyAlert from "../../../layouts/MyAlert";

function AddCourse() {
    const nav = useNavigate();

    const [courseName, setCourseName] = useState('');
    const [creditsNum, setCreditsNum] = useState(0);
    const [note, setNote] = useState('');

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    const saveCourse = async (e) => {
        e.preventDefault();
        if (courseName === '' || note === '' || creditsNum === null)
            showAlert('Vui lòng nhập đầy đủ thông tin', 'danger');
        else if (creditsNum <= 0 || creditsNum > 5)
            showAlert('Vượt quá tín chỉ cho phép', 'danger');
        else {
            const course = {
                courseName: courseName,
                creditsNum: creditsNum,
                note: note,
            };
            try{
                await CourseService.addCourse(course)
                showAlert('Thêm môn học thành công.');
            }catch (e) {
                if(e.response.status === 409)
                    showAlert('Tên môn học trùng.', 'danger')
                else
                    showAlert('Có lỗi xảy ra.', 'danger')

            }

        }
    };

    const changeCourseNameHandler = (e) => {
        setCourseName(e.target.value);
    }

    const changeCreditsNumHandler = (e) => {
        setCreditsNum(e.target.value);
    }

    const changeNoteHandler = (e) => {
        setNote(e.target.value);
    }

    const cancel = () => { nav(`/admin/course/all`); }

    return (
        <div>
            <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3">
                        <h3 className="App mt-2">Thêm môn học</h3>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group">
                                    <label>Tên môn học</label>
                                    <input placeholder="tên môn..." name="name" className="form-control"
                                           value={courseName} onChange={changeCourseNameHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Số tín chỉ</label>
                                    <input placeholder="tín chỉ..." name="credits" className="form-control"
                                           type="number" min="1" value={creditsNum} onChange={changeCreditsNumHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Ghi chú</label>
                                    <input placeholder="ghi chú..." name="note" className="form-control"
                                           value={note} onChange={changeNoteHandler}/>
                                </div>
                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={saveCourse}>Lưu</button>
                                    <button className="btn btn-secondary ms-1" onClick={cancel.bind(this)}>Trở về</button>
                                </div>
                            </form>
                        </div>
                        {alert && (
                            <MyAlert
                                message={alert.message}
                                color={alert.color}
                            />
                        )}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddCourse