import React, { useState } from 'react';

function Login({ onLogin }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (username === '' || password === '')
            setError('Vui lòng nhập đầy đủ thông tin');
        // Check username and password (replace with your actual authentication logic)
        else if (username === 'admin' && password === '123') {
            // Successful login
            setError('');
            onLogin(); // Call the onLogin function to set isAuthenticated to true
        } else {
            // Failed login
            setError('Sai tài khoản hoặc mật khẩu.');
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center">
            <form onSubmit={handleSubmit}>
                <img className="m-5" src="%PUBLIC_URL%/ou.png" alt="Logo" />
                <h3 className="mb-5 text-center">Đăng nhập</h3>
                <div className="mb-3">
                    <label htmlFor="username" className="form-label">Tài khoản:</label>
                    <input
                        type="text"
                        className="form-control"
                        id="username"
                        value={username}
                        onChange={handleUsernameChange}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Mật khẩu:</label>
                    <input
                        type="password"
                        className="form-control"
                        id="password"
                        value={password}
                        onChange={handlePasswordChange}
                    />
                </div>
                {error && <div className="alert alert-danger">{error}</div>}
                <button type="submit" className="btn btn-primary">Đăng nhập</button>
            </form>
        </div>
    );
}

export default Login;
