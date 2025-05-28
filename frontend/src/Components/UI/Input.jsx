import React from 'react'

const Input = ({placeholder}) => {
  return (
    <input
  type="text"
  placeholder={placeholder}
  className="w-full px-4 py-2 border border-gray-300 shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition duration-200"
/>

  )
}

export default Input