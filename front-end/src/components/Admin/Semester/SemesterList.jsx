import React, {useEffect, useState} from 'react';
import {Alert, Container, Table} from 'reactstrap';
import SemesterService from "../../../services/SemesterService";
import {useNavigate} from "react-router-dom";
import MyAlert from "../../../layouts/MyAlert";
import LectureService from "../../../services/LectureService";

function SemesterList() {
    const [semesters, setSemesters] = useState([]);
    const nav = useNavigate();
    const [success, setSuccess] = useState('');

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    useEffect(() => {
        const getAllSemester = async () => {
            try {
                const res = await SemesterService.getAllSemester();
                setSemesters(res.data);
            } catch (error) {
                showAlert('Có lỗi xảy ra', 'danger');
            }
        }
        getAllSemester();
    }, []);

    const getStatusText = (isFinish) => {
        return isFinish ? "Đã kết thúc" : "Chưa kết thúc";
    }

    const addSemester = () => { nav('/admin/semester/add'); }

    const deleteSemester = async (semester) => {
        try {
            if(!semester.finish) {
                const res = await SemesterService.deleteSemester(semester.id)
                if (res.data.success) {
                    setSemesters(semesters.filter(s => s.id !== semester.id));
                    showAlert(`Xóa thành công.`);
                }
            }
            else
                showAlert(`Học kỳ đã kết thúc.`, 'danger')
        } catch (error) {
            if(error.response.status === 404)
                showAlert(`Không tìm thấy học kỳ.`, 'danger');
            else if(error.response.status === 409)
                showAlert(`Học kỳ đã có dữ liệu.`, 'danger');
            else
                showAlert(`Lỗi không xác định`, 'danger');
        }
    }

    const setFinish = (semester) => {
        if (!semester.finish) {
            SemesterService.setFinish(semester.id).then(() => {
                setSemesters((prevSemesters) => {
                    return prevSemesters.map((s) => {
                        if (s.id === semester.id) {
                            return { ...s, finish: true };
                        }
                        return s;
                    });
                });
            });
            showAlert(`Thay đổi trạng thái thành công`);
        } else {
            showAlert(`Trạng thái đã được đánh dấu là đã kết thúc.`, 'warning');
        }
    }

    const updateSemester = (semester) => {
        nav(`/admin/semester/update/${semester.id}`);
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
                <h3 className ="App">Danh sách các học kỳ đang hoạt động</h3>
                <div className="row">
                    <Table className="mt-3 table table-striped table-bordered">
                        <thead className="text-center"><tr>
                            <th>Học kỳ</th>
                            <th>Ghi chú</th>
                            <th>Tình trạng</th>
                            <th>Thao tác</th>
                        </tr></thead>
                        <tbody>
                        { semesters.map( semester => (
                            <tr key={semester.id}>
                                <td>{semester.semesterName}</td>
                                <td>{semester.note}</td>
                                <td>{getStatusText(semester.finish)}</td>
                                <td className="text-center">
                                    <button className="btn-primary btn"
                                            onClick={() => {setFinish(semester)}}>Khóa học kỳ
                                    </button>
                                    <button className="ms-2 btn-success btn"
                                            onClick={() => {updateSemester(semester)}}>Chỉnh sửa
                                    </button>
                                    <button className="ms-2 btn-danger btn"
                                            onClick={() => {deleteSemester(semester)}}>Xóa
                                    </button>

                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </Table>
                </div>
                <div className="float-end row">
                    <button className="btn-primary btn"
                            onClick={addSemester}>Thêm
                    </button>
                </div>
            </Container>
        </div>
    );
}
export default SemesterList;