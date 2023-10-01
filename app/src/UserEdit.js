import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { useCookies } from 'react-cookie';

const UserEdit = () => {
    const initState = {
        fullName: '',
        email: '',
        phone: '',
        gender: '',
        dayOfBirth: '',
        major: ''
    };
    const [user, setUser] = useState(initState);
    const nav = useNavigate();
    const { id } = useParams();
    const [cookies] = useCookies(['XSRF-TOKEN']);

    useEffect(() => {
        if (id !== 'new') {
            fetch(`/api/v1/user/${id}`)
                .then(res => res.json())
                .then(data => setUser(data));
        }
    }, [id, setUser]);

    const handleChange = () => {
        const { name, value } = this.target
        setUser({ ...user, [name]: value })
    }

    const handleSubmit = async () => {
        this.preventDefault();

        await fetch(`/api/user${user.id ? `/${user.id}` : ''}`, {
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
        nav('/users');
    }

    const title = <h2>{user.id ? 'Edit user' : 'Add user'}</h2>;
    return (<div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="fullName">Name</Label>
                        <Input type="text" name="fullName" id="fullName" value={user.fullName || ''}
                               onChange={handleChange} autoComplete="fullName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Address</Label>
                        <Input type="text" name="email" id="email" value={user.email || ''}
                               onChange={handleChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="phone">City</Label>
                        <Input type="text" name="phone" id="phone" value={user.phone || ''}
                               onChange={handleChange} autoComplete="phone"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="gender">City</Label>
                        <Input type="text" name="gender" id="gender" value={user.gender || ''}
                               onChange={handleChange} autoComplete="gender"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="dayOfBirth">City</Label>
                        <Input type="text" name="dayOfBirth" id="dayOfBirth" value={user.dayOfBirth || ''}
                               onChange={handleChange} autoComplete="dayOfBirth"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="major">City</Label>
                        <Input type="text" name="major" id="major" value={user.major || ''}
                               onChange={handleChange} autoComplete="major"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Lưu</Button>{' '}
                        <Button color="secondary" tag={Link} to="/users">Hủy</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    )
};

export default UserEdit;