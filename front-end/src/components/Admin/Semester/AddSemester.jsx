import React, {useEffect, useState} from 'react'
import SemesterService from "../../../services/SemesterService";
import {useNavigate} from "react-router-dom";
import '../../../styles/App.css';
import {Alert, Button, Form, FormGroup, Input, Label} from "reactstrap";
import LectureService from "../../../services/LectureService";
import MyAlert from "../../../layouts/MyAlert";

function AddSemester() {
    const nav = useNavigate();
    const [semesterName, setSemesterName] = useState('');
    const [note, setNote] = useState('');

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    const saveSemester = async (e) => {
        e.preventDefault();
        if (semesterName === '') showAlert('Vui lòng nhập đầy đủ thông tin', 'warning');
        else {
            const semester = {
                semesterName,
                note,
            };

            try{
                await SemesterService.addSemester(semester)
                showAlert('Thêm giảng viên thành công.');
            }catch (e) {
                showAlert('Có lỗi xảy ra.', 'danger')
            }
        }
    };

    const changeSemesterNameHandler = (e) => {
        setSemesterName(e.target.value);
    }

    const changeNoteHandler = (e) => {
        setNote(e.target.value);
    }

    const cancel = () => { nav(`/admin/semester/available`); }

    return (
        <div>
            <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3">
                        <h3 className="App mt-2">Thêm học kỳ</h3>
                        <div className = "card-body">
                            <Form>
                                <FormGroup>
                                    <Label>Tên học kỳ</Label>
                                    <Input placeholder="học kỳ... khóa..." name="name" className="form-control"
                                           value={semesterName} onChange={changeSemesterNameHandler}/>
                                </FormGroup>
                                <FormGroup>
                                    <Label>Ghi chú</Label>
                                    <Input placeholder="ghi chú..." name="note" className="form-control"
                                           value={note} onChange={changeNoteHandler}/>
                                </FormGroup>
                                <div className="text-end mt-2">
                                    <Button color="primary" className="me-1" onClick={saveSemester}>Lưu</Button>
                                    <Button color="secondary" className="ms-1" onClick={cancel}>Hủy</Button>
                                </div>
                            </Form>
                        </div>
                        {alert && (
                            <MyAlert
                                message={alert.message}
                                color={alert.color}
                            />
                        )}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddSemester