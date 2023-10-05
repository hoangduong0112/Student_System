import React, {useEffect, useRef, useState} from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../app/AppNavbar';
import { Link } from 'react-router-dom';
import { useCookies } from 'react-cookie';
const UserList = () => {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);
    const [cookies] = useCookies(['XSRF-TOKEN']);

    const remove = async (id) => {
        await fetch(`/api/user/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': cookies['XSRF-TOKEN']
            },
            credentials:'include'
        }).then(() => {
            let updatedUsers = [...users].filter(i => i.id !== id);
            setUsers(updatedUsers);
        });
    }

    const userList = useRef([]);
    useEffect(() => {
        fetch("/api/user/info")
            .then(res => res.json())
            .then(data => {
                setUsers(data);
                setLoading(false);
            })

        userList.current = users.map(user => (
            <tr key={user.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{user.fullName}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{user.email}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{user.department_name}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{user.major_name}</td>
                <td><ButtonGroup>
                    <Button size="sn" color="primary" tag={Link}
                            to={"/api/user/update/" + user.id}>Edit</Button>
                    <Button size="sn" color="danger" onClick={
                        () => remove(user.id)}>Delete</Button>
                </ButtonGroup></td>
            </tr>
        ));
    }, []);

    if (loading) return <p>Loading...</p>;
    return (
        <div>
            <AppNavbar />
            <Container fluid>
                <div className="float-end">
                    <Button color="success" tag={Link} to="/api/user/new">
                        Add user
                    </Button>
                </div>
                <h3>Thông tin sinh viên</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="25%">Họ và Tên</th>
                        <th width="25%">Email</th>
                        <th width="25%">Khoa</th>
                        <th width="25%">Ngành</th>
                    </tr>
                    </thead>
                    <tbody>{userList.current}</tbody>
                </Table>
            </Container>
        </div>
    );
};
export default UserList;