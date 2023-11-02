import React, { useState, useEffect } from 'react';
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import SemesterService from "../../../services/SemesterService";
import {Alert, Button, Form, FormGroup, Input, Label} from "reactstrap";
import MyAlert from "../../../layouts/MyAlert";
import LectureService from "../../../services/LectureService";

function UpdateSemester() {
    const { id } = useParams();
    const loc = useLocation();
    const nav = useNavigate();
    const [resp, setResp] = useState('');

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };

    const [semesterName, setSemesterName] = useState( '');
    const [note, setNote] = useState('');

    useEffect(() => {
        const getSemesterById = async () => {
            try {
                const res = await SemesterService.getSemesterById(id);
                let semester = res.data;
                setSemesterName(semester.semesterName);
                setNote(semester.note);
            } catch (error) {
                if (error.response.status === 404)
                    showAlert('Không tìm thấy thông tin học kỳ', 'danger');
                else
                    showAlert('Lỗi không xác định', 'danger');
            }
        }
        getSemesterById()
    }, []);
    const updateSemester = async (e) => {
        e.preventDefault();
        if (semesterName === '') showAlert('Vui lòng nhập đầy đủ thông tin', 'warning');
        else {
            const semester = {
                semesterName: semesterName,
                note: note,
            };
            try {
                const res = await SemesterService.updateSemester(id, semester)
                showAlert('Chỉnh sửa học kỳ thành công.');
            } catch (error) {
                if(error.response.status === 404)
                    showAlert(`Không tim thấy học kỳ`, 'danger');
                else
                    showAlert(`Có lỗi xảy ra`, 'danger');
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
        <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3">
                            <h3 className="App mt-2">Chỉnh sửa học kỳ</h3>
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
                                        <Button color="primary" className="me-1" onClick={updateSemester}>Lưu</Button>
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
    )
}

export default UpdateSemester;