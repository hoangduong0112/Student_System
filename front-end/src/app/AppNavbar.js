import React, { useState } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';

const AppNavbar = () => {
    const [isOpen, setIsOpen] = useState(false);

    return (
        <Navbar color="primary">
            <NavbarBrand tag={Link} to="/">Trang chủ</NavbarBrand>
            <NavbarToggler onClick={() => {
                setIsOpen(!isOpen)
            }}/>
            <Collapse isOpen={isOpen} navbar>
                <Nav className="justify-content-end" style={{ width: "100%" }} navbar>
                    <NavItem>
                        <NavLink href="/api/guest/service-cate">Danh sách dịch vụ</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href="/api/user/info">Thông tin tài khoản</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href="/api/user/signout">Đăng xuất</NavLink>
                    </NavItem>
                </Nav>
            </Collapse>
        </Navbar>
    );
};

export default AppNavbar;