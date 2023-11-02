import React, {useEffect, useState} from 'react'
import CourseDataService from "../../../services/CourseDataService";
import {useNavigate, useParams} from "react-router-dom";
import '../../../styles/App.css';
import CourseService from "../../../services/CourseService";
import {Alert} from "reactstrap";
import {format, parse} from "date-fns";
import LectureService from "../../../services/LectureService";
import MyAlert from "../../../layouts/MyAlert";

function AddCourseData() {
    const nav = useNavigate();
    const [courses, setCourses] = useState([]);
    const [lectures, setLectures] = useState([]);

    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [courseId, setCourseId] = useState(0);
    const [lectureId, setLectureId] = useState(0);

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    useEffect(() => {
        getCourses().then();
        getLectures().then();
    }, [])

    const getCourses = async () => {
        try {
            const res = await CourseService.getAll();
            setCourses(res.data);
        } catch (err) {
            if(err.response.status === 404)
                showAlert('Không tìm thấy môn học: ', 'danger');
            else
                showAlert('Lỗi khi lấy danh sách môn học: ', 'danger');
        }
    };

    const getLectures = async () => {
        try {
            const res = await LectureService.getAllLecture();
            setLectures(res.data);
        } catch (err) {
            if(err.response.status === 404)
                showAlert('Không tìm thấy giảng viên ', 'danger');
            else
                showAlert('Lỗi khi lấy danh sách giảng viên ', 'danger');
        }
    };

    const saveCourseData = async (e) => {
        e.preventDefault();
        if (startDate === '' || endDate === '' || courseId === null || lectureId === null)
            showAlert('Vui lòng nhập đầy đủ thông tin', 'warning');
        else {
            const courseData = {
                startDate,//: format(parse(startDate, 'yyyyMMdd', new Date()), 'dd-MM-yyyy'),
                endDate,//: format(parse(endDate, 'yyyyMMdd', new Date()), 'dd-MM-yyyy'),
                courseId: courseId,
                lectureId: lectureId,
            }
            try{
                await CourseDataService.addCourse(courseData)
                showAlert('Thêm lớp học thành công.');
            }catch (e) {
                showAlert('Có lỗi xảy ra.', 'danger')

            }
        }
    };

    const changeStartDateHandler = (e) => {
        setStartDate(e.target.value);
    }

    const changeEndDateHandler = (e) => {
        setEndDate(e.target.value);
    }

    const changeCourseHandler = (e) => {
        setCourseId(parseInt(e.target.value));
    }

    const changeLectureHandler = (e) => {
        setLectureId(parseInt(e.target.value));
    }

    const cancel = () => { nav(`/admin/course-data/all`); }


    return (
        <div>
            <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3">
                        <h3 className="App mt-2">Thêm lớp học</h3>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group">
                                    <label>Ngày bắt đầu</label>
                                    <input name="startDate" className="form-control" min="2023-01-01" max="2024-12-31"
                                           type="date" value={startDate} onChange={changeStartDateHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Ngày kết thúc</label>
                                    <input name="endDate" className="form-control" min="2023-01-01" max="2024-12-31"
                                           type="date" value={endDate} onChange={changeEndDateHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Môn học</label>
                                    <select name="course" className="form-control custom-select"
                                            value={courseId} onChange={changeCourseHandler}>
                                        <option value="">Chọn môn học</option>
                                        {courses.map((course) => (
                                            <option key={course.id} value={course.id}>{course.courseName}</option>
                                        ))}
                                    </select>
                                </div>
                                <div className = "form-group">
                                    <label>Giảng viên</label>
                                    <select name="lecture" className="form-control custom-select"
                                            value={lectureId} onChange={changeLectureHandler}>
                                        <option value="">Chọn giảng viên</option>
                                        {lectures.map((lecture) => (
                                            <option key={lecture.id} value={lecture.id}>{lecture.lectureName}</option>
                                        ))}
                                    </select>
                                </div>
                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={saveCourseData}>Lưu</button>
                                    <button className="btn btn-secondary ms-1" onClick={cancel}>Trở về</button>
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

export default AddCourseData