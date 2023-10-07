import React from 'react';
import classNames from 'classnames';
import logo from './ou.png';

export default class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            active: "login",
            name: "",
            phone: "",
            role: "",
            email: "",
            password: "",
            onLogin: props.onLogin,
            onRegister: props.onRegister
        };
    };

    onChangeHandler = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        this.setState({[name] : value});
    };

    onSubmitLogin = (e) => {
        this.state.onLogin(e, this.state.email, this.state.password);
    };

    onSubmitRegister = (e) => {
        this.state.onRegister(e, this.state.name, this.state.email, this.state.phone, this.state.password);
    };

    render() {
        return (
            <div className="row justify-content-center border border-primary m-5">
                <div className="col-5">
                    <img className="mt-5 mb-5 App-logo" src={logo} alt="logo"/>
                    <ul className="nav nav-pills nav-justified mb-5" id="ex1" role="tablist">
                        <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", this.state.active === "login" ? "active" : "")} id="tab-login"
                                    onClick={() => this.setState({active: "login"})}>Đăng nhập</button>
                        </li>
                        <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", this.state.active === "register" ? "active" : "")} id="tab-register"
                                    onClick={() => this.setState({active: "register"})}>Đăng ký</button>
                        </li>
                    </ul>

                    <div className="tab-content">
                        <div className={classNames("tab-pane", "fade", this.state.active === "login" ? "show active" : "")} id="pills-login" >
                            <form onSubmit={this.onSubmitLogin}>

                                <div className="form-outline mb-4">
                                    <label className="form-label" htmlFor="loginName">Email</label>
                                    <input type="login" id="loginName" name= "login" className="form-control" onChange={this.onChangeHandler}/>
                                </div>

                                <div className="form-outline mb-5">
                                    <label className="form-label" htmlFor="loginPassword">Mật khẩu</label>
                                    <input type="password" id="loginPassword" name="password" className="form-control" onChange={this.onChangeHandler}/>
                                </div>

                                <div className="text-center">
                                    <button type="submit" className="btn btn-primary btn-block mb-5">Đăng nhập</button>
                                </div>


                            </form>
                        </div>
                        <div className={classNames("tab-pane", "fade", this.state.active === "register" ? "show active" : "")} id="pills-register" >
                            <form onSubmit={this.onSubmitRegister}>

                                <div className="form-outline mb-4">
                                    <label className="form-label" htmlFor="name">Họ và tên</label>
                                    <input type="text" id="name" name="name" className="form-control" onChange={this.onChangeHandler}/>
                                </div>

                                <div className="form-outline mb-4">
                                    <label className="form-label" htmlFor="phone">Số điện thoại</label>
                                    <input type="text" id="phone" name="phone" className="form-control" onChange={this.onChangeHandler}/>
                                </div>

                                <div className="form-outline mb-4">
                                    <label className="form-label" htmlFor="email">Email</label>
                                    <input type="text" id="email" name="email" className="form-control" onChange={this.onChangeHandler}/>
                                </div>

                                <div className="form-outline mb-5">
                                    <label className="form-label" htmlFor="registerPassword">Mật khẩu</label>
                                    <input type="password" id="registerPassword" name="password" className="form-control" onChange={this.onChangeHandler}/>
                                </div>

                                <div className="text-center">
                                    <button type="submit" className="btn btn-primary btn-block mb-5">Đăng ký</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    };

}