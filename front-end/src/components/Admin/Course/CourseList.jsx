import React, {useEffect, useState} from 'react';
import {Alert, Container, Table} from 'reactstrap';
import CourseService from "../../../services/CourseService";
import {useNavigate} from "react-router-dom";
import MyAlert from "../../../layouts/MyAlert";

function CourseList() {
    const [courses, setCourses] = useState([]);
    const nav = useNavigate();
    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await CourseService.getAll();
                setCourses(res.data);
            } catch (error) {
                showAlert('Có lỗi xảy ra', 'danger');
            }
        }
        fetchData();
    }, []);


    const addCourse = () => { nav('/admin/course/add'); }

    const deleteCourse = async (course) => {
        try {
            const res = await CourseService.deleteCourse(course.id);
            if(res.data.success) {
                setCourses(courses.filter(c => c.id !== course.id));
                showAlert(`Xóa ${course.courseName} thành công.`);
            }
        } catch (error) {
            if(error.response.status === 404)
                showAlert(`Không tìm thấy môn học.`, 'danger');
            else if(error.response.status === 409)
                showAlert(`Xóa thất bại vì môn học đã có lớp.`, 'danger');
            else
                showAlert(`Lỗi khi xóa ${course.courseName}`, 'danger');
        }
    }
    const updateCourse = (course) => {
        nav(`/admin/course/update/${course.id}`);
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
                <h3 className ="App">Danh sách môn học</h3>
                <div className="row">
                    <Table className="mt-3 table table-striped table-bordered">
                        <thead className="text-center"><tr>
                            <th>Tên môn học</th>
                            <th>Số tín chỉ</th>
                            <th>Ghi chú</th>
                            <th>Thao tác</th>
                        </tr></thead>
                        <tbody>
                        { courses.map( course => (
                            <tr key={course.id}>
                                <td>{course.courseName}</td>
                                <td>{course.creditsNum}</td>
                                <td>{course.note}</td>
                                <td className="text-center">
                                    <button className="btn-success btn"
                                            onClick={() => {updateCourse(course)}}>Chỉnh sửa
                                    </button>
                                    <button className="ms-2 btn-danger btn"
                                            onClick={()=> {deleteCourse(course)}}>Xóa
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </Table>
                </div>
                <div className="float-end row">
                    <button className="btn-primary btn"
                            onClick={addCourse}>Thêm
                    </button>
                </div>
            </Container>
        </div>
    );
}
export default CourseList;