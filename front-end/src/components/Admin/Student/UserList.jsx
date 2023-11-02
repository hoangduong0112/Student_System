import React, {useEffect, useState} from 'react';
import {Button, Container, Row, Table} from 'reactstrap';
import UserService from "../../../services/UserService";
import {useNavigate, useParams} from "react-router-dom";

function StudentList() {
    const [students, setStudents] = useState([]);
    const nav = useNavigate();

    useEffect(() => {
            UserService.getAllUser().then(res => {
                setStudents(res.data);
            });

    }, []);

    return (
        <Container fluid className='mb-5 pd-5'>
            <h3 className ="App">Danh sách các sinh viên</h3>
            <Row>
                <Table className="mt-3 table-striped table-bordered">
                    <thead className="App"><tr>
                        <th>Họ và tên</th>
                        <th>Email</th>
                        <th>Khoa</th>
                        <th>Ngành</th>
                    </tr></thead>
                    <tbody>
                    { students.map( student => (
                        <tr key={student.id}>
                            <td>{student.fullName}</td>
                            <td>{student.email}</td>
                            <td>{student.department_name}</td>
                            <td>{student.major_name}</td>
                        </tr>
                    ))}
                    </tbody>
                </Table>
            </Row>
            <Row className="float-end">
                <Button color="primary"
                        onClick={() => {nav('/home')}}>Quay lại
                </Button>
            </Row>
        </Container>
    );
}
export default StudentList;