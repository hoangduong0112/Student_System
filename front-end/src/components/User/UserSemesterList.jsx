import React, {useContext, useEffect, useState} from 'react';
import {Container, Table} from 'reactstrap';
import {useNavigate} from "react-router-dom";
import UserService from "../../services/UserService";
import SemesterService from "../../services/SemesterService";
import {UserContext} from "../../app/App";
import MyAlert from "../../layouts/MyAlert";

function SemesterList() {
    const [semesters, setSemesters] = useState([]);
    const [available, setAvailable] = useState([]);
    const [valueSemester, setValueSemester] =  useState(0);
    const nav = useNavigate();
    const [user, setUser] = useContext(UserContext);
    const [lever, setLever] = useState(false);

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };


    useEffect(() => {
        const getMySemester = async () => {
            try{
                let res = await UserService.getMySemester()
                setSemesters(res.data)
            }catch(error){
                showAlert('Không lấy được dữ liệu')
            }
        }
        getMySemester()
    }, [lever]);

    useEffect(() => {
        const getSemesters = async () => {
            try {
                const res = await SemesterService.getAvailableSemester();
                setAvailable(res.data);
            } catch (error) {
                showAlert('Lỗi khi lấy danh sách học kỳ ', 'danger');
            }
        };

        getSemesters().then();
    }, [])

    const viewSemester = (id) => { nav('/user/semester/' + id + '/course'); }
    const goBack = () => { nav('/home'); }

    const changeAvailableSemesterHandler = (e) => {
        setValueSemester(parseInt(e.target.value));
    }

    const addSemesterForUser = async () => {
        try {
            const res = await SemesterService.addSemesterUser(valueSemester);
            if (res.data.success) {
                setLever(!lever);
            }
        } catch (error) {
            showAlert('Lỗi hệ thống', 'danger');
        }
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
                <h3 className ="App">Xem thời khóa biểu học kỳ</h3>
                {!semesters ? <>
                    <h3 className='display-6 m-3'>Không có học kỳ nào!</h3>
                </> : <>
                    <div className="row">
                        <Table className="mt-3 table table-striped table-bordered">
                            <thead className="text-center"><tr>
                                <th>Học kỳ</th>
                                <th>Trạng thái</th>
                                <th>Thao tác</th>
                            </tr></thead>
                            <tbody>
                            { semesters.map( semester => (
                                <tr key={semester.id}>
                                    <td>{semester.semesterName}</td>
                                    <td>{semester.status}{/* ? 'Còn hoạt động' : 'Đã kết thúc'*/}</td>
                                    <td className='text-center'>
                                        <button className="btn-primary btn"
                                                onClick={() => viewSemester(semester.id)}>Xem
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </Table>
                    </div>
                </>}
                <div className="float-start row">
                    <div className = "form-group">
                        <label>Thêm học kỳ</label>
                        <select name="available" className="form-control"
                                value={valueSemester} onChange={changeAvailableSemesterHandler}>
                            <option value="">Chọn học kỳ</option>
                            {available.map(a => (
                                <option key={a.id} value={a.id}>{a.semesterName}</option>
                            ))}
                        </select>
                        <button className="btn-primary btn"
                                onClick={addSemesterForUser}>Thêm học kỳ sinh viên
                        </button>
                    </div>
                </div>

                <div className="float-end row">
                    <button className="btn-primary btn"
                            onClick={goBack}>Quay lại
                    </button>
                </div>
            </Container>
        </div>
    );
}
export default SemesterList;