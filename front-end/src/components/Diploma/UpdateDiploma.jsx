import React, {useState, useEffect, useContext} from 'react';
import DiplomaService from '../../services/DiplomaService';
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import MyAlert from "../../layouts/MyAlert";
import OnlineService from "../../services/OnlineService";
import {Table} from "reactstrap";
import moment from "moment/moment";
import {UserContext} from "../../app/App";

function UpdateDiploma() {
    const { id } = useParams();
    const loc = useLocation();
    const nav = useNavigate();
    const [alert, setAlert] = useState(null);
    const [service, setService] = useState({});
    const [lever, setLever] = useState(false);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };
    const { success } = loc.state || false;

    const [diplomaId, setDiplomaId] = useState(0);
    const [copyInput, setCopyInput] = useState(0);
    const [phoneContactInput, setPhoneContactInput] = useState( '');
    const [emailInput, setEmailInput] = useState('');
    const [diplomaYearInput, setDiplomaYearInput] = useState( 1970);
    const [diplomaCodeInput, setDiplomaCodeInput] = useState('');

    useEffect(() => {
        const getDiplomaById = async () => {
            try {
                let diploma = await DiplomaService.getDiploma(id)
                setDiplomaId(diploma.data.id);
                setCopyInput(diploma.data.copy);
                setPhoneContactInput(diploma.data.phoneContact);
                setEmailInput(diploma.data.email);
                setDiplomaYearInput(diploma.data.diplomaYear);
                setDiplomaCodeInput(diploma.data.diplomaCode);
            }catch (error){
                showAlert('Lỗi khi lấy dữ liệu', 'danger');
            }
        }
        getDiplomaById()
    }, [id]);


    useEffect(() => {
        if (success) {
            showAlert('Gửi yêu cầu thành công');
        }
    }, [success]);


    const updateDiploma = async (e) => {
        e.preventDefault();
        if (phoneContactInput === undefined || copyInput === undefined || emailInput === undefined
            || diplomaYearInput === undefined || diplomaCodeInput === undefined)
            showAlert('Vui lòng nhập đầy đủ thông tin', 'danger');
        else if (copyInput <= 0 || diplomaYearInput < 1970)
            showAlert('Số không hợp lệ', 'danger');
        else {
            const diploma = {
                copy: copyInput,
                phoneContact: phoneContactInput,
                email: emailInput,
                diplomaYear: diplomaYearInput,
                diplomaCode: diplomaCodeInput,
            };
            try{
                const res = await DiplomaService.updateDiploma(diploma, diplomaId)
                setLever(!lever)
                showAlert('Chỉnh sửa thành công')
            }catch (error) {
                if(error.response.status === 404)
                    showAlert('Không tìm thấy yêu cầu', 'danger')
                else if(error.response.status === 403)
                    showAlert('Bạn không có quyền làm điều này', 'danger')
                else if(error.response.status === 409)
                    showAlert('Yêu cầu đã được xử lý hoặc hủy', 'danger')
                else
                    showAlert('Lỗi hệ thống', 'danger')
            }
        }
    }
    useEffect(() => {
        const getOnlineService = async () => {
            try {
                const res = await OnlineService.getRequestById(id);
                setService(res.data)
            } catch (error) {
                showAlert('Lỗi khi lấy thông tin yêu cầu', 'danger');
            }
        };

        getOnlineService();
    }, [lever]);
    const changeCopyHandler = (e) => {
        setCopyInput(parseInt(e.target.value));
    };

    const changePhoneHandler = (e) => {
        setPhoneContactInput(e.target.value);
    };

    const changeEmailHandler = (e) => {
        setEmailInput(e.target.value);
    };

    const changeYearHandler = (e) => {
        setDiplomaYearInput(parseInt(e.target.value));
    };

    const changeCodeHandler = (e) => {
        setDiplomaCodeInput(e.target.value);
    };

    const cancel = () => { nav(`/home`); }

    return (
        <div>
            <div className="container">
                <div className="row">
                    <Table className="mt-3 table table-striped table-bordered">
                        <thead className="text-center"><tr>
                            <th>Giá tiền</th>
                            <th>Ngày yêu cầu</th>
                            <th>Trạng thái</th>
                            <th>Tình trạng</th>
                        </tr></thead>
                        <tbody>
                        <tr key={service.id}>
                            <td>{service.price}</td>
                            <td>{moment(service.createdDate, "YYYY-MM-DD").format("DD-MM-YYYY")}</td>
                            <td>{service.serviceCateName}</td>
                            <td>{service.status}</td>
                        </tr>
                        </tbody>
                    </Table>
                </div>
                <div className="row">
                    <div className="card col-md-6 offset-md-3">
                        <h3 className="text-center mt-2">Chỉnh sửa bản sao bằng tốt nghiệp</h3>
                        {alert && (
                            <MyAlert
                                message={alert.message}
                                color={alert.color}
                            />
                        )}
                        <div className="card-body">
                            <form>
                                <div className="form-group">
                                    <label>Số lượng bản sao</label>
                                    <input placeholder="Copy" name="copy" type="number" min="1" className="form-control"
                                           value={copyInput} onChange={changeCopyHandler} />
                                </div>
                                <div className="form-group">
                                    <label>Số điện thoại</label>
                                    <input placeholder="0123456789" name="phoneContact" className="form-control"
                                           value={phoneContactInput} onChange={changePhoneHandler} />
                                </div>
                                <div className="form-group">
                                    <label>Email</label>
                                    <input placeholder="Địa chỉ email" name="email" className="form-control"
                                           value={emailInput} onChange={changeEmailHandler} />
                                </div>
                                <div className="form-group">
                                    <label>Năm tốt nghiệp</label>
                                    <input placeholder="20xx" name="year" type="number" min="1970" className="form-control"
                                           value={diplomaYearInput} onChange={changeYearHandler} />
                                </div>
                                <div className="form-group">
                                    <label>Mã bằng</label>
                                    <input placeholder="123..." name="code" className="form-control"
                                           value={diplomaCodeInput} onChange={changeCodeHandler} />
                                </div>
                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={updateDiploma} >Chỉnh sửa</button>
                                    <button className="btn btn-secondary ms-1" onClick={cancel} >Hủy</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    );
}

export default UpdateDiploma;