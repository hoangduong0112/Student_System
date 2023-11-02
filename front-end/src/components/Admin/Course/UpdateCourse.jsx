import React, { useState, useEffect } from 'react';
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import CourseService from "../../../services/CourseService";
import MyAlert from "../../../layouts/MyAlert";

function UpdateCourse() {
    const { id } = useParams();
    const loc = useLocation();
    const nav = useNavigate();

    const [courseNameInput, setCourseNameInput] = useState('');
    const [creditsNumInput, setCreditsNumInput] = useState( '');
    const [noteInput, setNoteInput] = useState( '');

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    useEffect(() => {
        const getCourseById = async () => {
            try {
                const res = await CourseService.getById(id);
                setCourseNameInput(res.data.courseName);
                setCreditsNumInput(res.data.creditsNum);
                setNoteInput(res.data.note);
            } catch (error) {
                showAlert('Có lỗi xảy ra', 'danger');
            }
        }
        getCourseById()
    }, [id]);

    const updateCourse = async (e) => {
        e.preventDefault();
        if (courseNameInput === '' || creditsNumInput === null)
            showAlert('Vui lòng nhập đầy đủ thông tin', 'danger');
        else if (creditsNumInput <= 0 || creditsNumInput > 5)
            showAlert('Số tín chỉ không hợp lệ', 'danger');
        else {
            const course = {
            courseName: courseNameInput,
            creditsNum: creditsNumInput,
            note: noteInput,
        };
            try {
                const res = await CourseService.updateCourse(id, course)
                showAlert('Chỉnh sửa thành công');
            } catch (error) {
                if(error.response.status === 409)
                    showAlert(`Tên môn học trùng ${error.response}`, 'danger');
                else if(error.response.status === 404)
                    showAlert(`Không tim thấy môn học ${error.response}`, 'danger');
                else
                    showAlert(`Có lỗi xảy ra`, 'danger');
            }
        }
    };

    const changeCourseNameHandler = (e) => {
        setCourseNameInput(e.target.value);
    }

    const changeCreditsNumHandler = (e) => {
        setCreditsNumInput(e.target.value);
    }

    const changeNoteHandler = (e) => {
        setNoteInput(e.target.value);
    }

    const cancel = () => { nav(`/admin/course/all`); }

    return (
        <div>
            <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3">
                        <h3 className="App mt-2">Chỉnh sửa môn học</h3>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group">
                                    <label>Tên môn học: </label>
                                    <input placeholder="tên môn..." name="name" className="form-control"
                                          value={courseNameInput} onChange={changeCourseNameHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Số tín chỉ: </label>
                                    <input placeholder="tín chỉ..." name="credits" className="form-control"
                                           value={creditsNumInput} onChange={changeCreditsNumHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Ghi chú: </label>
                                    <input placeholder="ghi chú..." name="note" className="form-control"
                                           value={noteInput} onChange={changeNoteHandler}/>
                                </div>
                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={updateCourse}>Lưu</button>
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

export default UpdateCourse;
