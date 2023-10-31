import React from "react";

import { Alert } from 'reactstrap';

const MyAlert = ({ message, color }) => {
    return (
        <Alert color={color} style={{marginTop:"5%", marginRight:"10%", marginLeft:"10%"}}
               onMouseEnter={() => ('')}>
            {message}
        </Alert>
    );
};

export default MyAlert;