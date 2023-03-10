import React, { useState } from 'react';

function App() {
  const [user, setUser] = useState({});
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/users', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: {
          firstName: "Petko",
          lastName: "Petkov",
          email: "testmail@gmail.com",
          password: "test123",
          phone: "123456789",
          address: "adresa"
        }
      });
      const data = await response.json();
      console.log(data)
      // handle success
    } catch (error) {
      // handle error
      console.log(error)
    }
  }
  return (
    <div>
      <form onSubmit={handleSubmit}>
        {/* form inputs for user data */}
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default App;