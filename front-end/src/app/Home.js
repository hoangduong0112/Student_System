import React, {useContext, useEffect, useState} from 'react';
import {Container, Table, Button, ButtonGroup} from 'reactstrap';
import UserService from '../services/UserService';
import { useNavigate } from 'react-router-dom';
import moment from "moment/moment";
import OnlineService from "../services/OnlineService";
import {UserContext} from "./App";
import SemesterService from "../services/SemesterService";
import MyAlert from "../layouts/MyAlert";

const Home = () => {
    const [loading, setLoading] = useState(false);
    const [user, setUser] = useContext(UserContext);
    const nav = useNavigate();
    const [semesters, setSemesters] = useState([]);
    const [requests, setRequests] = useState([]);
    const [hoveredText, setHoveredText] = useState('');

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({message, color});
    };

    const signin = async () => {
        await setUser({'type': 'signout'});
        nav('/guest/auth/signin');
    };

    const viewDetails = (id) => {
        nav('/user/semester/' + id + '/course');
    }

    const mySemester = () => {
        nav('/user/semester/')
    }
    const viewCates = () => {
        nav('/service-cate');
    }
    const viewInfo = () => {
        nav('/user/info');
    }
    const viewServices = () => {
        nav('/moderator/get-request');
    }

    const viewCate = () => {
        nav('/moderator/service-cate');
    }
    const viewCourses = () => {
        nav('/admin/course/all');
    }
    const viewCourseDatas = () => {
        nav('/admin/course-data/all');
    }
    const viewLecture = () => {
        nav('/admin/lecture/all');
    }
    const viewSemesters = () => {
        nav('/admin/semester/available');
    }
    const viewStuds = () => {
        nav('/admin/student');
    }
    const viewDepts = () => {
        nav('/admin/department');
    }
    const updateRequest = (request) => {
        if ((request.serviceCateName).includes('bảng điểm'))
            nav(`/service/transcript/update/${request.id}`);
        else if ((request.serviceCateName).includes('CNSV'))
            nav(`/service/stud-cert/update/${request.id}`);
        else if ((request.serviceCateName).includes('BTN'))
            nav(`/service/diploma/update/${request.id}`);
        else if ((request.serviceCateName).includes('Mở khóa'))
            nav(`/service/unlock-stud/update/${request.id}`);
    }

    const createPayment = (request) => {
        nav(`/user/payment/create?service=${request.id}`);
    }

    const viewPayment = (request) => {
        nav(`/user/payment/detail/${request.paymentId}`);
    }
    const requestDetail = (request) => {
        if ((request.serviceCateName).includes('bảng điểm'))
            nav(`/service/transcript/${request.id}`);
        else if ((request.serviceCateName).includes('CNSV'))
            nav(`/service/stud-cert/${request.id}`);
        else if ((request.serviceCateName).includes('BTN'))
            nav(`/service/diploma/${request.id}`);
        else if ((request.serviceCateName).includes('Mở khóa'))
            nav(`/service/unlock-stud/${request.id}`);
    }
    const cancelRequest = (request) => {
        if (request.paymentId === 0) {
            OnlineService.cancelRequest(request.id).then((res) => {
                const updatedRequest = res.data;
                setRequests((prev) => {
                    return prev.map((s) => {
                        if (s.id === request.id) {
                            return updatedRequest;
                        }
                        return s;
                    });
                });
            });
            showAlert(`Thay đổi trạng thái thành công`, null);
        } else {
            showAlert(`Thay đổi trạng thái thất bại.`, 'danger');
        }
    }


    useEffect(() => {
        const getUserSemester = async () => {
            try {
                setLoading(true);
                const res = await UserService.getMySemester();
                await setSemesters(res.data);
            } catch (error) {
                console.error('Lỗi lấy thông tin học kỳ: ', error);
            } finally {
                setLoading(false);
            }
        }

        getUserSemester();
    }, []);

    useEffect(() => {
        const getRequest = async () => {
            try {
                setLoading(true);
                const res = await UserService.getRequest();
                await setRequests(res.data);
            } catch (error) {
                console.error('Lỗi lấy thông tin yêu cầu: ', error);
            } finally {
                setLoading(false);
            }
        }

        getRequest();
    }, []);


    if (user) {
        return (
            <div className="pb-5">
                <Container fluid>
                    {loading ? (
                        <p className="display-6 m-2">Loading...</p>
                    ) : (
                        <div>
                            {user.role === "MODERATOR" && <>
                                <ButtonGroup className="me-5 btn-group border border-secondary rounded-pill p-1">
                                    <button className="btn btn-success rounded-pill"
                                            onClick={viewServices}>Quản lý yêu cầu
                                    </button>
                                    <button className="btn btn-success rounded-pill"
                                            onClick={viewCate}>Quản lý dịch vụ
                                    </button>
                                </ButtonGroup>
                            </>}
                            {user.role === "ADMIN" ? <>
                            <span className="btn-group border border-secondary rounded-pill p-1">
                                <button className="me-1 btn btn-success rounded-pill rounded-end-0"
                                        onClick={viewStuds}>Quản lý sinh viên</button>
                                <button className="me-1 btn btn-success rounded-start-0 rounded-end-0"
                                        onClick={viewDepts}>Quản lý khoa</button>
                                <button className="me-1 btn btn-success rounded-start-0 rounded-end-0"
                                        onClick={viewSemesters}>Quản lý học kỳ</button>
                                <button className="me-1 btn btn-success rounded-pill rounded-start-0 rounded-end-0"
                                        onClick={viewCourses}>Quản lý môn học</button>
                                <button className="me-1 btn btn-success rounded-pill rounded-start-0 rounded-end-0"
                                        onClick={viewLecture}>Quản lý giảng viên</button>
                                <button className="btn btn-success rounded-pill rounded-start-0"
                                        onClick={viewCourseDatas}>Quản lý lớp học</button>
                            </span>
                            </> : <></>}

                            <h2 className='App'>
                                Xin chào, {user.fullName}
                            </h2>
                            <h5 className="my-3 pb-2 border-bottom">
                                Các học kỳ</h5>
                            <p className="btn btn-success rounded-pill rounded-start-0"
                                    onClick={mySemester}>xem chi tiết</p>

                            {semesters.length ? (<>
                                    {semesters.map((semester) => (
                                        <span className="list-group-horizontal row-cols-4"
                                              key={semester.id}>
                                            <div className="list-inline-item mx-1">
                                                <div className="btn fw-bold"
                                                     style={{color: hoveredText === semester.semesterName ? 'cyan' : ''}}
                                                     data-toggle="tooltip" title="Nhấn vào để xem chi tiết"
                                                     onMouseEnter={() => setHoveredText(semester.semesterName)}
                                                     onMouseLeave={() => setHoveredText('')}
                                                     onClick={() => viewDetails(semester.id)}>
                                                    {semester.semesterName}
                                                </div>
                                            </div>
                                        </span>
                                    ))}
                                </>
                            ) : (
                                <div className="App row">
                                                <span
                                                    className="bg-warning fw-bold text-black p-2">Chưa mở học kỳ</span>
                                </div>
                            )}
                            <h5 className="my-3 pb-2 border-bottom">Lịch sử đăng ký dịch vụ
                                <button className="float-end h5 border-0 bg-white"
                                        onMouseEnter={() => setHoveredText('cyan')}
                                        onMouseLeave={() => setHoveredText('')}
                                        data-toggle="tooltip" title="Xem danh sách dịch vụ trực tuyến"
                                        onClick={viewCates} style={{color: hoveredText}}>Các dịch vụ
                                </button>
                            </h5>
                            {alert && (
                                <MyAlert
                                    message={alert.message}
                                    color={alert.color}
                                />
                            )}
                            {requests.length ? (
                                <div className="row">
                                    <Table className="mt-3 table table-striped table-bordered">
                                        <thead className="text-center">
                                        <tr>
                                            <th>Dịch vụ</th>
                                            <th>Ngày yêu cầu</th>
                                            <th>Trạng thái</th>
                                            <th>Thành tiền</th>
                                            <th>Thanh toán</th>
                                            <th>Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {requests.map((request) => (
                                            <tr key={request.id}>
                                                <td>{request.serviceCateName}</td>
                                                <td>{moment(request.createdDate, "YYYY-MM-DD").format("DD-MM-YYYY")}</td>
                                                <td>{request.status}</td>
                                                <td>{request.price}</td>
                                                <td>
                                                    {request.paymentId === 0 ?
                                                        <button className="btn-secondary btn" onClick={() => {
                                                            createPayment(request)
                                                        }}>Thanh toán ngay
                                                        </button>
                                                        : <button className="btn-info btn" onClick={() => {
                                                            viewPayment(request)
                                                        }}>Thông tin thanh toán
                                                        </button>
                                                    }
                                                </td>
                                                {(request.status === 'PENDING' && request.paymentId === 0) && (
                                                    <td className="text-center">
                                                        <button className="btn-success btn"
                                                                onClick={() => updateRequest(request)}>
                                                            Chỉnh sửa
                                                        </button>
                                                        <button className="ms-2 btn-danger btn"
                                                                onClick={() => cancelRequest(request)}>
                                                            Hủy
                                                        </button>
                                                    </td>
                                                )}
                                                {(request.status === 'ACCEPT' || request.status === 'CANCEL' || request.paymentId !== 0) && (
                                                    <td className="text-center">
                                                        <button className="btn-info btn"
                                                                onClick={() => requestDetail(request)}>
                                                            Thông tin
                                                        </button>
                                                    </td>
                                                )}
                                            </tr>
                                        ))}
                                        </tbody>
                                    </Table>
                                </div>
                            ) : (
                                <div className="App row">
                                            <span
                                                className="bg-warning fw-bold text-black p-2">Chưa đăng ký dịch vụ nào</span>
                                </div>
                            )}
                            <button className="border-0 bg-white h5 my-3 pb-2 border-bottom"
                                    data-toggle="tooltip" title="Nhấn vào để xem chi tiết"
                                    onClick={viewInfo}>Thông tin sinh viên
                            </button>
                            <div>
                                <span className="fw-bold ps-4">Khoa: </span>
                                <span>{user.department_name}</span>
                            </div>
                            <div>
                                <span className="fw-bold ps-4">Ngành: </span>
                                <span>{user.major_name}</span>
                            </div>
                        </div>
                    )}
                </Container>
            </div>
        );
    } else {
        return (<>
            <div className="pb-5">
                <Container fluid>
                <h2>Vui lòng đăng nhập!</h2>
                <ButtonGroup>
                <Button className="my-3 bg-primary" onClick={signin}>Đăng nhập</Button>
                </ButtonGroup></Container>
            </div>
            </>
        )
    }
}



export default Home;
