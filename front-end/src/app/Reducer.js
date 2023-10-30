import cookie from 'react-cookies';

const MyUserReducer = (currentState, action) => {
    switch (action.type) {
        case 'signin':
            return action.payload;

        case 'signout': {
            cookie.remove('token', {path: '/'});
            cookie.remove('user', {path: '/'});
            return null;
        }
    }
    return currentState;
}

export default MyUserReducer;