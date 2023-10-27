import React, {useEffect, useState} from 'react';
import {Alert, Container, Table} from 'reactstrap';
import LectureService from "../../../services/LectureService";
import {useNavigate} from "react-router-dom";

function LectureList() {
    const [lectures, setLectures] = useState([]);
    const nav = useNavigate();
    const [success, setSuccess] = useState('');

    useEffect(() => {
        LectureService.getAllLecture().then((res) => {
            setLectures(res.data);
        });
    }, []);

    const addLecture = () => { nav('/admin/lecture/add'); }

    const deleteLecture = (lecture) => {
        LectureService.deleteLecture(lecture.id)
            .then(() => {
                setLectures(lectures.filter(l => l.id !== lecture.id));
                setSuccess(`Xóa ${lecture.lectureName} thành công.`);
            })
            .catch((error) => {
                if (error.response) {
                    if (error.response.status === 409) {
                        setSuccess("Xảy ra xung đột. Không thể thực hiện thao tác này.");
                    } else if (error.response.data) {
                        const errorMessage = error.response.data.message;
                        setSuccess(errorMessage);
                    } else {
                        setSuccess("Xảy ra lỗi không xác định.");
                    }
                } else {
                    setSuccess("Không thể kết nối đến máy chủ.");
                }
            });
    }

    const updateLecture = (lecture) => {
        nav(`/admin/lecture/update/${lecture.id}`, {
            state: {
                lectureName: lecture.lectureName,
                lecturePhone: lecture.lecturePhone,
            }
        });
    }

    return (
        <div className='mb-5'>
            <Container fluid>
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
                {success && <Alert color="success" className="fixed-bottom"
                                   style={{marginBottom:'100px', marginLeft:'200px', marginRight:'200px'}}
                                   onMouseEnter={() => setSuccess('')}>{success}</Alert>}

            </Container>
        </div>
    );
}
export default LectureList;