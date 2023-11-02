import React, {useEffect, useState} from 'react';
import {Alert, Container, Table} from 'reactstrap';
import CourseDataService from "../../../services/CourseDataService";
import {useNavigate} from "react-router-dom";
import { format, parse } from 'date-fns';
import CourseService from "../../../services/CourseService";
import MyAlert from "../../../layouts/MyAlert";

function CourseDataList() {
    const [courseDatas, setCourseDatas] = useState([]);
    const nav = useNavigate();

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    useEffect(() => {
        const getCourseData = async () => {
            try {
                const res = await CourseDataService.getAllCourse();
                setCourseDatas(res.data);
            } catch (error) {
                showAlert('Có lỗi xảy ra', 'danger');
            }
        }
        getCourseData();
    }, []);

    const addCourseData = () => { nav('/admin/course-data/add'); }

    const deleteCourseData = async (data) => {
        try {
            const res = await CourseDataService.deleteCourse(data.id)
            if(res.data.success) {
                setCourseDatas(courseDatas.filter(courseData => courseData.id !== data.id));
                showAlert(`Xóa thành công.`);
            }
        } catch (error) {
            if(error.response.status === 404)
                showAlert(`Không tìm thấy lớp học.`, 'danger');
            else if(error.response.status === 409)
                showAlert(`Xóa thất bại vì lớp học đã có sinh viên.`, 'danger');
            else
                showAlert(`Xóa thất bại, lỗi chưa xác định.`, 'danger');
        }
    }

    const updateCourseData = (courseData) => {
        nav(`/admin/course-data/update/${courseData.id}`);
    }

    return (
        <div className='mb-5'>
            <Container fluid>
                {alert && (
                    <MyAlert
                        message={alert.message}
                        color={alert.color}
                    />
                )}
                <h3 className ="App">Thông tin lớp học</h3>
                <div className="row">
                    <Table className="mt-3 table table-striped table-bordered">
                        <thead className="text-center"><tr>
                            <th>Tên môn học</th>
                            <th>Ngày bắt đầu</th>
                            <th>Ngày kết thúc</th>
                            <th>Giảng viên</th>
                            <th>Thao tác</th>
                        </tr></thead>
                        <tbody>
                        { courseDatas.map( courseData => (
                            <tr key={courseData.id}>
                                <td>{courseData.course.courseName}</td>
                                <td>{new Date(courseData.startDate).toLocaleDateString('en-GB')}</td>
                                <td>{new Date(courseData.endDate).toLocaleDateString('en-GB')}</td>
                                <td>{courseData.lecture.lectureName}</td>
                                <td className="text-center">
                                    <button className="btn-success btn"
                                            onClick={() => {updateCourseData(courseData)}}>Chỉnh sửa
                                    </button>
                                    <button className="ms-2 btn-danger btn"
                                            onClick={() => {deleteCourseData(courseData)}}>Xóa
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </Table>
                </div>
                <div className="float-end row">
                    <button className="btn-primary btn"
                            onClick={addCourseData}>Thêm
                    </button>
                </div>
            </Container>
        </div>
    );
}
export default CourseDataList;