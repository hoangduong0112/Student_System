import {useLocation, useNavigate, useParams} from "react-router-dom";
import React, {useContext, useEffect, useState} from "react";
import StudCertificateService from "../../services/StudCertificateService";
import {Alert, ButtonGroup, Container, Table} from "reactstrap";
import PaymentService from "../../services/PaymentService";
import moment from "moment";
import OnlineService from "../../services/OnlineService";
import {UserContext} from "../../app/App";
import MyAlert from "../../layouts/MyAlert";

function StudCertificateDetail() {
    const { id } = useParams();
    const nav = useNavigate();
    const [result, setResult] = useState('');
    const [payment, setPayment] = useState('');
    const [service, setService] = useState('');
    const [user, setUser] = useContext(UserContext);
    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    const getResult = async () => {
        try {
            const res = await StudCertificateService.getStudCertificate(id);
            setResult(res.data);
            return res.data;
        } catch (error) {
            showAlert('Lỗi khi lấy chi tiết yêu cầu', 'danger')
            return null;
        }
    };
    const getPayment = async (id) => {
        try {
            const res = await PaymentService.verifyPayment(id);

            setPayment(res.data);
        } catch (error) {
            setPayment(null)
        }
    };

    const handleConfirmRequest = async () => {
        try {
            const res = await OnlineService.acceptRequest(service.id);

            setService(res.data);
            showAlert('Xác nhận yêu cầu thành công', '');
        } catch (error) {
            showAlert('Lỗi khi xác nhận thông tin', 'danger');
        }
    };
    const getService = async (id) => {
        try {
            const res = await OnlineService.getRequestById(id);
            setService(res.data);
        } catch (error) {
            showAlert('Lỗi khi lấy thông tin yêu cầu', 'danger');
        }
    };
    useEffect(() => {
        const runEffects = async () => {
            const resultData = await getResult();
            if(resultData !== null) {
                await getPayment(resultData.onlineServiceId);
                await getService(resultData.onlineServiceId);
            }
        };

        runEffects();
    }, []);

    return (
        <div>
            { result ? (
                <Container fluid>
                    <h3 className ="App">Tình trạng yêu cầu</h3>
                    {alert && (
                        <MyAlert
                            message={alert.message}
                            color={alert.color}
                        />
                    )}
                    <div className="row">
                        <Table className="mt-3 table table-striped table-bordered">
                            <thead className="text-center"><tr>
                                <th>Giá tiền</th>
                                <th>Ngày yêu cầu</th>
                                <th>Trạng thái</th>
                                <th>Tình trạng</th>
                                <th>Thao tác</th>
                            </tr></thead>
                            <tbody>
                            <tr key={service.id}>
                                <td>{service.price}</td>
                                <td>{moment(service.createdDate, "YYYY-MM-DD").format("DD-MM-YYYY")}</td>
                                <td>{service.serviceCateName}</td>
                                <td>{service.status}</td>
                                <td>
                                {user.role === "MODERATOR" && <>
                                        <button className="btn btn-success rounded-pill"
                                                onClick={handleConfirmRequest}>Xác nhận yêu cầu</button>
                                </>}
                                {(user.role === "USER") &&<>
                                        <td><button className="mx-5 btn-primary btn">
                                            Hủy yêu cầu - hoàn tiền (Chưa phát triển)
                                        </button></td>
                                </>}
                                </td>
                            </tr>
                            </tbody>
                        </Table>
                    </div>
                    <h3 className="App">Thông tin yêu cầu cấp chứng nhận sinh viên</h3>
                    <div className="row">
                        <Table className="mt-5">
                            <tbody>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Mã yêu cầu:</th>
                                <td>{result.onlineServiceId}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Số lượng bản sao (Tiếng Việt):</th>
                                <td>{result.vietCopy}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Số lượng bản sao (Tiếng Anh):</th>
                                <td>{result.engCopy}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Số điện thoại liên lạc:</th>
                                <td>{result.phoneContact}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Email:</th>
                                <td>{result.email}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Nội dung:</th>
                                <td>{result.content}</td>
                            </tr>
                            </tbody>
                        </Table>

                            <div>
                                <h3 className="App">Chi tiết thanh toán</h3>
                                {payment ? (
                                <div className="row">
                                    <Table className="mt-3 table table-striped table-bordered">
                                        <thead className="text-center">
                                        <tr>
                                            <th>Giá tiền</th>
                                            <th>Ngày thanh toán</th>
                                            <th>Trạng thái</th>
                                            <th>Mã hóa đơn VNPay</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr key={payment.id}>
                                            <td>{payment.price}</td>
                                            <td>{moment(payment.createdDate, "YYYY-MM-DD").format("DD-MM-YYYY")}</td>
                                            <td>{payment.status}</td>
                                            <td>{payment.vnpayTxnred}</td>
                                        </tr>
                                        </tbody>
                                    </Table>
                                </div>
                                ) : <p>Chưa có dữ liệu.</p>}
                            </div>
                    </div>
                </Container>
            ) : (
                <h3>Loading...</h3>
            )}
        </div>
    );
}

export default StudCertificateDetail;