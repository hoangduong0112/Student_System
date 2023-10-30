import {useLocation, useNavigate, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import DiplomaService from "../../services/DiplomaService";
import {Alert, Container, Table} from "reactstrap";
import PaymentService from "../../services/PaymentService";
import moment from "moment";
import OnlineService from "../../services/OnlineService";
import MySpinner from "../../layouts/Spinner";
function DiplomaDetail() {
    const { id } = useParams();
    const nav = useNavigate();
    const [result, setResult] = useState({});
    const [payment, setPayment] = useState({});
    const [service, setService] = useState({});
    const [isLoading, setIsLoading] = useState(false);


    const getResult = async () => {
        try {
            const res = await DiplomaService.getDiploma(id);
            setResult(res.data);
            return res.data;
        } catch (error) {
            console.error('Lỗi dữ liệu:', error);
            return null;
        }
    };
    const getPayment = async (id) => {
        try {
            const res = await PaymentService.getByServiceId(id);
            setPayment(res.data);
        } catch (error) {
            console.error('Lỗi dữ liệu:', error);
        }
    };
    const getService = async (id) => {
        try {
            const res = await OnlineService.getRequestById(id);
            setService(res.data);
        } catch (error) {
            console.error('Lỗi dữ liệu:', error);
        }
    };
    useEffect(() => {
        const runEffects = async () => {
            const resultData = await getResult();
            console.log(resultData)
            await getPayment(resultData.onlineServiceId);
            await getService(resultData.onlineServiceId);
        };

        runEffects();
    }, []);

    const handleConfirmRequest = async () => {
        setIsLoading(true);

        // Sử dụng setTimeout để đảm bảo Spinner được hiển thị trước khi gọi API
        setTimeout(async () => {
            try {
                const res = await OnlineService.acceptRequest(service.id);

                // Cập nhật state với dữ liệu mới trả về từ API
                setService(res.data);
                setIsLoading(false);
            } catch (error) {
                console.error('Lỗi khi cập nhật tình trạng:', error);
                setIsLoading(false);
            }
        }, 0);
    };


    return (
        <div>
            { result ? (
                <Container fluid>
                    {isLoading && <MySpinner />}
                    <h3 className ="App">Tình trạng yêu cầu</h3>
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
                                <td><button className="mx-5 btn-primary btn" onClick={handleConfirmRequest}>
                                    Xác nhận yêu cầu
                                </button>
                                        </td>
                            </tr>
                            </tbody>
                        </Table>
                    </div>
                    <h3 className="App">Thông tin yêu cầu cấp bản sao bằng tốt nghiệp</h3>
                    <div className="row">
                        <Table className="mt-5">
                            <tbody>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Mã yêu cầu</th>
                                <td>{result.onlineServiceId}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Số lượng bản sao cần:</th>
                                <td>{result.copy}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Số điện thoại:</th>
                                <td>{result.phoneContact}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Email:</th>
                                <td>{result.email}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Năm tốt nghiệp:</th>
                                <td>{result.diplomaYear}</td>
                            </tr>
                            <tr className="border-bottom" style={{ height: '50px' }}>
                                <th>Mã tốt nghiệp:</th>
                                <td>{result.diplomaCode}</td>
                            </tr>



                            </tbody>
                        </Table>
                        <h3 className ="App">Chi tiết thanh toán</h3>
                        <div className="row">
                            <Table className="mt-3 table table-striped table-bordered">
                                <thead className="text-center"><tr>
                                    <th>Giá tiền</th>
                                    <th>Ngày thanh toán</th>
                                    <th>Trạng thái</th>
                                    <th>Mã hóa đơn VNPay</th>
                                    <th></th>
                                </tr></thead>
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
                    </div>
                </Container>
            ) : (
                <h3>Loading...</h3>
            )}
        </div>
    );
}

export default DiplomaDetail;