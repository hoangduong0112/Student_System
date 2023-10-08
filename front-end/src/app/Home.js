import React, { useEffect, useState } from 'react';
import '../styles/App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import { useCookies } from 'react-cookie';

const Home = () => {

    const [authenticated, setAuthenticated] = useState(false);
    const [loading, setLoading] = useState(false);
    const [user, setUser] = useState(undefined);
    const [cookies] = useCookies(['XSRF-TOKEN']); // <.>

    useEffect(() => {
        setLoading(true);
        fetch('api/user', { credentials: 'include' }) // <.>
            .then(response => response.text())
            .then(body => {
                if (body === '') setAuthenticated(false);
                else {
                    setUser(JSON.parse(body));
                    setAuthenticated(true);
                }
                setLoading(false);
            });
    }, [setAuthenticated, setLoading, setUser])

    const signin = () => {
        let port = (window.location.port ? ':' + window.location.port : '');
        if (port === ':3000') port = ':8080';
        window.location.href = `//${window.location.hostname}${port}/api/private`;
    }

    const signout = () => {
        fetch('/api/user/signout', {
            method: 'POST', credentials: 'include',
            headers: { 'X-XSRF-TOKEN': cookies['XSRF-TOKEN'] } // <.>
        })
            .then(res => res.json())
            .then(response => {
                window.location.href = `${response.text()}?id_token_hint=${response.text()}`
                    + `&post_logout_redirect_uri=${window.location.origin}`;
            });
    }

    const message = user ?
        <h2>Xin chào, {user.fullName}</h2> :
        <p>Vui lòng đăng nhập!</p>;

    const button = authenticated ?
        <div className='App'>
            <Button color="link"><Link to="/api/admin">Admin</Link></Button>
            <br/>
            <Button color="link" onClick={signout}>Đăng xuất</Button>
        </div> :
        <Button color="primary" onClick={signin}>Đăng nhập</Button>;

    if (loading) return <p>Loading...</p>;
    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                {message}
                {button}
            </Container>
        </div>
    );
}

export default Home;