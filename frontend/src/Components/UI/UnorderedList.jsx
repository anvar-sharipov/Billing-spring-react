import React from 'react'

const UnorderedList = ({ items, renderItem, className = '' }) => {
  return (
    <ul className={`list-disc pl-6 space-y-2 text-gray-800 ${className}`}>
      {items.map((item) => (
        <li
          key={item.id}
          className="bg-white p-3 rounded-lg shadow-sm hover:bg-gray-50 transition"
        >
          {renderItem ? renderItem(item) : item}
        </li>
      ))}
    </ul>
  )
}

export default UnorderedList