import React, {useContext, useState, useEffect} from 'react';
import logo from '../styles/image/ou.png';
import {useNavigate} from 'react-router-dom';
import {UserContext} from "./App";
import {Alert, Button, Card, CardBody, Form, FormGroup, Input, Label} from "reactstrap";
import AuthService, {auth, endpoints} from "../services/AuthService";
import cookie from "react-cookies";
import MyAlert from "../layouts/MyAlert";


function Signin() {
    const [user, setUser] = useContext(UserContext);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const nav = useNavigate();

    const [alert, setAlert] = useState(null);
    const showAlert = (message, color) => {
        setAlert({ message, color });
    };


    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleSubmit = async (    e) => {
        e.preventDefault();
        if (email === '' || password === '')
            showAlert('Vui lòng nhập đầy đủ thông tin.', 'danger');
        else {
            try {
                const res = await AuthService.post(endpoints['signin'], {
                    'email': email,
                    'password': password
                });

                cookie.save('token', res.data, {path: '/'});

                const info = await auth().get(endpoints['user']);
                cookie.save('user', info.data, {path: '/'});

                await setUser({
                    'type': 'signin',
                    'payload': info.data
                });

                if (user !== null) {
                    showAlert('Đăng nhập thành công.', null);
                    nav('/home');
                }
            } catch (error) {
                // Xử lý lỗi ở đây
                showAlert('Đăng nhập thất bại.', 'danger');
            }
        }
    }

    return (
        <Card className="bg-primary shadow-lg py-2 rounded-5"
              style={{marginLeft: "20%", marginRight: "20%"}}>
            <CardBody className="px-4 rounded-5">
                <CardBody className="bg-white d-flex justify-content-center rounded-5">
                    <Form onSubmit={handleSubmit}>
                        <img className="App-logo mx-auto my-3 d-block rounded-5" src={logo} alt="logo"/>
                        <h2 className="mb-4">Đăng nhập vào hệ thống</h2>
                        <FormGroup className="mb-3">
                            <Label>Tài khoản</Label>
                            <Input type="text" value={email} onChange={handleEmailChange} />
                        </FormGroup>
                        <FormGroup className="mb-3">
                            <Label>Mật khẩu</Label>
                            <Input type="password" value={password} onChange={handlePasswordChange} />
                        </FormGroup>
                        {alert && (
                            <MyAlert
                                message={alert.message}
                                color={alert.color}
                            />
                        )}
                        <div className="text-end">
                            <Button color="primary" type="submit" className="mb-5">Đăng nhập</Button>
                        </div>
                    </Form>
                </CardBody>
            </CardBody>
        </Card>
    );
}

export default Signin