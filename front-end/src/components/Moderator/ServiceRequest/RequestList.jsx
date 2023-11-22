import OnlineService from "../../../services/OnlineService";
import PaymentDetails from "../../Payment/PaymentDetails";
import {Alert, Button, Container, FormGroup, Input, Label, Row, Table} from 'reactstrap';
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import React, {useEffect, useState} from "react";
import moment from "moment/moment";
import PaymentService from "../../../services/PaymentService";
import ServiceCate from "../../../services/ServiceCate";
import ModerateService from "../../../services/ServiceCate";
import MyAlert from "../../../layouts/MyAlert";

function RequestList(){
    const [allRequests, setAllRequests] = useState([]);
    const [requests, setRequests] = useState([]);
    const [cates, setCates] = useState([]);
    const nav = useNavigate();
    const [selectedCate, setSelectedCate] = useState({});

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };


    useEffect(() => {
        OnlineService.getAllRequest().then((res) => {
            const allRequestsData = res.data;
            setAllRequests(allRequestsData);
            setRequests(allRequestsData);
        }).catch((error) => {
            if (error.response.status === 403) {
                showAlert('Bạn không có quyền làm điều này', 'danger')
            } else {
                showAlert('Có lỗi xảy ra', 'danger')
            }
        });
        ServiceCate.getAllServiceCate().then(res => {
            setCates(res.data);
        }).catch((error) => {
            if ( error.response.status === 403) {
                showAlert('Bạn không có quyền làm điều này', 'danger')
            } else {
                showAlert('Có lỗi xảy ra', 'danger')
            }
        });
    }, []);

    const selectChange = (e) => {
        const selectedValue = e.target.value;
        setSelectedCate(selectedValue);

        const filteredRequests = allRequests.filter(request => request.serviceCateName === selectedValue);
        setRequests(filteredRequests);
    }

    const requestDetail = (request) => {
        if ((request.serviceCateName).includes('bảng điểm'))
            nav(`/service/transcript/${request.id}`, { state: { request } });
        else if ((request.serviceCateName).includes('CNSV'))
            nav(`/service/stud-cert/${request.id}`);
        else if ((request.serviceCateName).includes('BTN'))
            nav(`/service/diploma/${request.id}`);
        else if ((request.serviceCateName).includes('Mở khóa'))
            nav(`/service/unlock-stud/${request.id}`);

    }
    const deleteRequest = (request) => {
        OnlineService.deleteRequest(request.id).then(() => {
            setRequests(requests.filter(r => r.id !== request.id));
        })
        setAlert(`Xóa yêu cầu thành công.`)
    }
    return (
        <div className='mb-5'>
            {alert && (
                <MyAlert
                    message={alert.message}
                    color={alert.color}
                />
            )}
            <Container fluid>
                <h3 className="App mt-2">Quản lý dịch vụ</h3>
                <FormGroup>
                    <Label className="m-1">Lọc dịch vụ</Label>
                    <Input
                        type="select"
                        name="cate"
                        className="custom-select rounded-3 p-1"
                        value={selectedCate}
                        onChange={selectChange}
                    >
                        <option value="">Chọn loại</option>
                        {cates.map((cate) => (
                            <option key={cate.serviceCateName} value={cate.serviceCateName}>{cate.serviceCateName}</option>
                        ))}
                    </Input>
                </FormGroup>
                <Row>
                <div className="row">
                    <Table className="mt-3 table table-striped table-bordered">
                        <thead className="text-center"><tr>
                            <th>Dịch vụ</th>
                            <th>Ngày yêu cầu</th>
                            <th>Trạng thái</th>
                            <th>Thành tiền</th>
                            <th></th>
                        </tr></thead>
                        <tbody>
                        {requests.map((request) => (
                            <tr key={request.id}>
                                <td>{request.serviceCateName}</td>
                                <td>{moment(request.createdDate, "YYYY-MM-DD").format("DD-MM-YYYY")}</td>
                                <td>{request.status}</td>
                                <td>{request.price}</td>

                                <td><button className="btn-secondary btn" onClick={() => {
                                    requestDetail(request)}}>Thông tin chi tiết
                                </button></td>
                            </tr>
                        ))}
                        </tbody>
                    </Table>
                </div>

                </Row>
            </Container>
        </div>
    );
}
export default RequestList;