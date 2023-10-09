import React, { useContext, useState } from 'react'
import { UserContext } from './UserContext'


const UserProvider = ({children}) => {
   const [user,setUser] = useState({})

  return (
    <UserContext.Provider value={user}>
        {children}
    </UserContext.Provider>
  )
}

export default UserProvider
