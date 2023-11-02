import React, {useEffect, useState} from 'react';
import {Alert, Container, Table} from 'reactstrap';
import LectureService from "../../../services/LectureService";
import {useNavigate} from "react-router-dom";
import MyAlert from "../../../layouts/MyAlert";

function LectureList() {
    const [lectures, setLectures] = useState([]);
    const nav = useNavigate();

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    useEffect(() => {
        const getAllLectures = async () => {
            try {
                const res = await LectureService.getAllLecture();
                setLectures(res.data);
            } catch (error) {
                showAlert('Có lỗi xảy ra', 'danger');
            }
        }
        getAllLectures();

    }, []);

    const addLecture = () => { nav('/admin/lecture/add'); }

    const deleteLecture = async (lecture) => {
        try {
            const res = await LectureService.deleteLecture(lecture.id)
            if(res.data.success) {
                setLectures(lectures.filter(l => l.id !== lecture.id));
                showAlert(`Xóa ${lecture.lectureName} thành công.`);
            }
        } catch (error) {
            if(error.response.status === 404)
                showAlert(`Không tìm thấy giảng viên.`, 'danger');
            else if(error.response.status === 409)
                showAlert(`Xóa thất bại vì giảng viên đã giảng dạy.`, 'danger');
            else
                showAlert(`Lỗi không xác định`, 'danger');
        }
    }

    const updateLecture = (lecture) => {
        nav(`/admin/lecture/update/${lecture.id}`);
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
                <h3 className ="App">Danh sách giảng viên</h3>
                <div className="row">
                    <Table className="mt-3 table table-striped table-bordered">
                        <thead className="text-center"><tr>
                            <th>Tên giảng viên</th>
                            <th>Số điện thoại</th>
                        </tr></thead>
                        <tbody>
                        { lectures.map( lecture => (
                            <tr key={lecture.id}>
                                <td>{lecture.lectureName}</td>
                                <td>{lecture.lecturePhone}</td>
                                <td className="text-center">
                                    <button className="btn-success btn"
                                            onClick={() => {updateLecture(lecture)}}>Chỉnh sửa
                                    </button>
                                    <button className="ms-2 btn-danger btn"
                                            onClick={()=> {deleteLecture(lecture)}}>Xóa
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </Table>
                </div>
                <div className="float-end row">
                    <button className="btn-primary btn"
                            onClick={addLecture}>Thêm
                    </button>
                </div>
            </Container>
        </div>
    );
}
export default LectureList;