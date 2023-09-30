import logo from './logo.svg';
import './App.css';
import React, { useEffect,useState } from 'react';

const App = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetch('api/users').then(res => res.json())
        .then(data => {
          setUsers(data);
          setLoading(false);
        })
  }, []);

  if (loading) { return <p>Loading...</p>; }
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div className="App-intro">
          <h2 className="text-center">Thông tin sinh viên</h2>
            { users.map(user => <div key={user.id}>
                {user.fullName}
            </div>) }
        </div>
      </header>
    </div>
  );
}

export default App;
