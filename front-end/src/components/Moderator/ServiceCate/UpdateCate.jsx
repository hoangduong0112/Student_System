import React, { useState, useEffect } from 'react';
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import ServiceCate from "../../../services/ServiceCate";
import {Alert, Button, Card, CardBody, Container, Form, FormGroup, Input, Label, Row} from "reactstrap";
import MyAlert from "../../../layouts/MyAlert";

function UpdateCate() {
    const { id } = useParams();
    const loc = useLocation();
    const nav = useNavigate();

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    const {serviceCateName, price, description, isAvailable, numOfDate} = {};
    const [serviceCateNameInput, setServiceCateNameInput] = useState(serviceCateName || '');
    const [priceInput, setPriceInput] = useState(price || 0);
    const [descriptionInput, setDescriptionInput] = useState(description || '');
    const [isAvailableInput, setIsAvailableInput] = useState(isAvailable || false);
    const [numOfDateInput, setNumOfDateInput] = useState(numOfDate || 0);

    useEffect(() => {
        const getById = async () => {
            try {
                const res = await ServiceCate.getById(id);
                let cate = res.data;
                setServiceCateNameInput(cate.serviceCateName);
                setPriceInput(cate.price);
                setDescriptionInput(cate.description);
                setIsAvailableInput(cate.isAvailable);
                setNumOfDateInput(cate.numOfDate);
            } catch (error) {
                showAlert('Lỗi khi lấy dữ liệu', 'danger');
            }
        };

        getById();
    }, []);


    const saveCate = async (e) => {
        e.preventDefault();
        if (serviceCateNameInput === '' || priceInput === '' || descriptionInput === '' || numOfDateInput === '')
            showAlert('Vui lòng nhập đầy đủ thông tin', 'warning');
        else if (priceInput.toString() <= 0 || numOfDateInput.toString() <= 0)
            showAlert('Số nhập không hợp lệ', 'warning');
        else {
            const cate = {
                serviceCateName: serviceCateNameInput,
                price: priceInput,
                description: descriptionInput,
                isAvailable: isAvailableInput,
                numOfDate: numOfDateInput,
            };
            try{
                await  ServiceCate.updateCate(cate, id)
                showAlert('Chỉnh sửa loại dịch vụ thành công.');
            }catch (error){
                if(error.response.status === 404)
                    showAlert('Không tìm thấy dữ liệu');
                else
                    showAlert('Lỗi hệ thống');
            }

        }
    }
    const changeServiceCateNameHandler = (e) => { setServiceCateNameInput(e.target.value); }

    const changePriceHandler = (e) => { setPriceInput(parseFloat(e.target.value)); }

    const changeDescriptionHandler = (e) => { setDescriptionInput(e.target.value); }

    const changeIsAvailableHandler = (e) => { setIsAvailableInput(e.target.value); }

    const changeDateHandler = (e) => { setNumOfDateInput(parseInt(e.target.value)); }

    const cancel = () => { nav('/moderator/service-cate'); }

    return (
        <Container fluid>
            <Row className="mt-3">
                <Card className="col-md-6 offset-md-3">
                    {alert && (
                        <MyAlert
                            message={alert.message}
                            color={alert.color}
                        />
                    )}
                    <Row className="justify-content-center pb-2 mt-2 border-bottom h3">Chỉnh sửa dịch vụ</Row>
                    <CardBody>
                        <Form>
                            <FormGroup>
                                <Label>Tên dịch vụ</Label>
                                <Input placeholder="dịch vụ muốn chỉnh sửa..." name="serviceCateName" className="form-control"
                                       value={serviceCateNameInput} onChange={changeServiceCateNameHandler}/>
                            </FormGroup>
                            <FormGroup>
                                <Label>Thành tiền</Label>
                                <Input placeholder="giá tiền..." name="price" type="number" min="1000" className="form-control"
                                       value={priceInput} onChange={changePriceHandler}/>
                            </FormGroup>
                            <FormGroup>
                                <Label>Nội dung</Label>
                                <Input placeholder="nội dung..." name="description" className="form-control"
                                       value={descriptionInput} onChange={changeDescriptionHandler}/>
                            </FormGroup>
                            <FormGroup check>
                                <Label check>
                                    <Input type="checkbox" checked={isAvailableInput}
                                           onChange={changeIsAvailableHandler} />Còn mở
                                </Label>
                            </FormGroup>
                            <FormGroup>
                                <Label>Thời gian cấp</Label>
                                <Input placeholder="số ngày..." name="numDates" className="form-control"
                                       value={numOfDateInput} onChange={changeDateHandler}/>
                            </FormGroup>
                            <div className="text-end mt-2">
                                <Button color="primary" className="m-1" onClick={saveCate}>Lưu</Button>
                                <Button color="secondary" className="m-1" onClick={cancel}>Hủy</Button>
                            </div>
                        </Form>
                    </CardBody>
                </Card>
            </Row>
        </Container>
    )
}

export default UpdateCate;