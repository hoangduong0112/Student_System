import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../app/AppNavbar';
import { useCookies } from 'react-cookie';

const UserEdit = () => {
    const initState = {
        fullName: '',
        email: '',
        department_name: '',
        major_name: ''
    };
    const [user, setUser] = useState(initState);
    const nav = useNavigate();
    const { id } = useParams();
    const [cookies] = useCookies(['XSRF-TOKEN']);

    useEffect(() => {
        if (id !== 'new') {
            fetch(`/api/user/info/${id}`)
                .then(res => res.json())
                .then(data => setUser(data));
        }
    }, [id, setUser]);

    const handleChange = (event) => {
        const { name, value } = event.target
        setUser({ ...user, [name]: value })
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        await fetch(`/api/user/info${user.id ? `/${user.id}` : ''}`, {
            method: (user.id) ? 'PUT' : 'POST',
            headers: {
                'X-XSRF-TOKEN': cookies['XSRF-TOKEN'],
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user),
            credentials: 'include'
        });
        setUser(initState);
        nav('/');
    }

    const title = <h2>{user.id ? 'Edit user' : 'Add user'}</h2>;
    return (<div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="fullName">Họ và Tên</Label>
                        <Input type="text" name="fullName" id="fullName" value={user.fullName || ''}
                               onChange={handleChange} autoComplete="fullName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="text" name="email" id="email" value={user.email || ''}
                               onChange={handleChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="dayOfBirth">Khoa</Label>
                        <Input type="text" name="department_name" id="department_name" value={user.department_name || ''}
                               onChange={handleChange} autoComplete="department_name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="major">Ngành</Label>
                        <Input type="text" name="major_name" id="major_name" value={user.major_name || ''}
                               onChange={handleChange} autoComplete="major_name"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Lưu</Button>{' '}
                        <Button color="secondary" tag={Link} to="/api/user/info">Hủy</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    )
};

export default UserEdit;