import React, { useState, useEffect } from 'react';
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import CourseDataService from "../../../services/CourseDataService";
import CourseService from "../../../services/CourseService";
import LectureService from "../../../services/LectureService";
import MyAlert from "../../../layouts/MyAlert";

function UpdateCourseData() {
    const { id } = useParams();
    const loc = useLocation();
    const nav = useNavigate();
    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };
    const [courses, setCourses] = useState([]);
    const [lectures, setLectures] = useState([]);

    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [courseId, setCourseId] = useState(0);
    const [lectureId, setLectureId] = useState( 0);

    useEffect(() => {
        const getCourseDataById = async () => {
            try {
                const res = await CourseDataService.getById(id);
                setStartDate(res.data.startDate);
                setEndDate(res.data.endDate);
                setCourseId(res.data.course.id);
                setLectureId(res.data.lecture.id);
            } catch (error) {
                showAlert('Có lỗi xảy ra', 'danger');
            }
        }
        getCourseDataById();
        getCourses();
        getLectures();
    }, [])

    const getCourses = async () => {
        try {
            const res = await CourseService.getAll();
            setCourses(res.data);
        } catch (error) {
            showAlert('Lỗi khi lấy danh sách môn học', 'danger');
        }
    };

    const getLectures = async () => {
        try {
            const res = await LectureService.getAllLecture();
            setLectures(res.data);
        } catch (error) {
            showAlert('Lỗi khi lấy danh sách giảng viên', 'danger');
        }
    };

    const updateCourseData = async (e) => {
        e.preventDefault();
        if (startDate === '' || endDate === '' || courseId === '' || lectureId === '')
            showAlert('Vui lòng nhập đầy đủ thông tin', 'warning');
        else if (courseId <= 0)
            showAlert('Không có mã môn học này trong dữ liệu', 'danger');
        else if (lectureId <= 0)
            showAlert('Không có mã giảng viên này trong dữ liệu', 'danger');
        else {
            const courseData = {
                startDate: startDate,
                endDate: endDate,
                courseId: courseId,
                lectureId: lectureId,
            };

            try {
                const res = await CourseDataService.updateCourse(id, courseData)
                showAlert('Chỉnh sửa thành công');
            } catch (error) {
                if(error.response.status === 404)
                    showAlert(`Không tim thấy môn học ${error.response}`, 'danger');
                else
                    showAlert(`Có lỗi xảy ra`, 'danger');
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
                {alert && (
                    <MyAlert
                        message={alert.message}
                        color={alert.color}
                    />
                )}
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3">
                        <h3 className="App mt-2">Chỉnh sửa lớp học</h3>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group">
                                    <label>Ngày bắt đầu</label>
                                    <input name="startDate" className="form-control" min="2023-01-01"
                                           type="date" value={startDate} onChange={changeStartDateHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Ngày kết thúc</label>
                                    <input name="endDate" className="form-control" min="2023-01-01"
                                           type="date" value={endDate} onChange={changeEndDateHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Môn học</label>
                                    <select name="course" className="form-control custom-select"
                                            value={courseId} onChange={changeCourseHandler}>
                                        Chọn môn học
                                        {courses.map((course) => (
                                            <option key={course.id} value={course.id}>{course.courseName}</option>
                                        ))}
                                    </select>
                                </div>
                                <div className = "form-group">
                                    <label>Giảng viên</label>
                                    <select name="lecture" className="form-control custom-select"
                                            value={lectureId} onChange={changeLectureHandler}>
                                        Chọn giảng viên
                                        {lectures.map((lecture) => (
                                            <option key={lecture.id} value={lecture.id}>{lecture.lectureName}</option>
                                        ))}
                                    </select>
                                </div>
                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={updateCourseData}>Lưu</button>
                                    <button className="btn btn-secondary ms-1" onClick={cancel}>Trở lại</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default UpdateCourseData;