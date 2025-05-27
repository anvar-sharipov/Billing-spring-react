import React, { useEffect, useState, useRef } from "react";

const AdminEtraps = () => {
  const [etraps, setEtraps] = useState([]);
  const [newEtrap, setNewEtrap] = useState("");
  const [editPk, setEditPk] = useState(null);
  const [editName, setEditName] = useState("");

  const editInputRef = useRef(null); // 🔹 Шаг 1

  useEffect(() => {
    fetch("http://localhost:8080/api/etraps")
      .then((res) => res.json())
      .then((res) => setEtraps(res))
      .catch(console.error);
  }, []);

  useEffect(() => {
    if (editPk !== null && editInputRef.current) {
      editInputRef.current.focus(); // 🔹 Фокус
      editInputRef.current.select(); // 🔹 Выделение текста
    }
  }, [editPk]);

  const addNewEtrap = () => {
    if (!newEtrap.trim()) return;
    fetch("http://localhost:8080/api/etraps", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ etrap: newEtrap }),
    })
      .then((res) => res.json())
      .then((added) => setEtraps((p) => [...p, added]))
      .catch(console.error);
    setNewEtrap("");
  };

  const deleteEtrap = (id) => {
    fetch("http://localhost:8080/api/etraps/" + id, {
      method: "DELETE",
    }).then(() => setEtraps((p) => p.filter((e) => e.id !== id)));
  };

  const startEdit = (etrap) => {
    setEditPk(etrap.id);
    setEditName(etrap.etrap);
  };

  const saveEdit = (pk) => {
    fetch("http://localhost:8080/api/etraps/" + pk, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ etrap: editName }),
    })
      .then((res) => res.json())
      .then((updated) => {
        setEtraps(etraps.map((e) => (e.id === pk ? updated : e)));
        setEditPk(null);
        setEditName("");
      });
  };

  return (
    <div>
      <div className="text-2xl font-bold text-blue-600">Hello, Tailwind!</div>

      <label>
        New etrap:
        <input
          type="text"
          onChange={(e) => setNewEtrap(e.target.value)}
          value={newEtrap}
        />
      </label>
      <button onClick={addNewEtrap} disabled={!newEtrap.trim()}>
        Add
      </button>
      <ul>
        {etraps.map((etrap) => (
          <li key={etrap.id}>
            {editPk === etrap.id ? (
              <>
                <input
                  type="text"
                  ref={editInputRef} // 🔹 Привязка ссылки
                  onChange={(e) => setEditName(e.target.value)}
                  value={editName}
                />
                <button onClick={() => saveEdit(etrap.id)}>Save</button>
                <button onClick={(e) => setEditPk(null)}>Cancel</button>
              </>
            ) : (
              <>
                {etrap.etrap}
                <button onClick={() => startEdit(etrap)}>Edit</button>
                <button onClick={() => deleteEtrap(etrap.id)}>Delete</button>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AdminEtraps;

// import React, { useEffect, useState } from 'react';

// export default function AdminEtraps() {
//   const [etraps, setEtraps] = useState([]);
//   const [newEtrap, setNewEtrap] = useState('');
//   const [editing, setEditing] = useState(null);
//   const [editName, setEditName] = useState('');

//   useEffect(() => {
//     fetch('http://localhost:8080/api/etraps')
//       .then(res => res.json())
//       .then(res => setEtraps(res))
//       .catch(console.error);
//   }, []);

//   const addEtrap = () => {
//     if (!newEtrap.trim()) return;
//     fetch('http://localhost:8080/api/etraps', {
//       method: 'POST',
//       headers: { 'Content-Type': 'application/json' },
//       body: JSON.stringify({ etrap: newEtrap }),
//     })
//       .then(res => res.json())
//       .then(added => {
//         setEtraps([...etraps, added]);
//         setNewEtrap('');
//       });
//   };

//   const deleteEtrap = (id) => {
//     fetch(`http://localhost:8080/api/etraps/${id}`, { method: 'DELETE' })
//       .then(() => setEtraps(etraps.filter(e => e.id !== id)));
//   };

//   const startEdit = (etrap) => {
//     setEditing(etrap.id);
//     setEditName(etrap.etrap);
//   };

//   const saveEdit = (id) => {
//     fetch(`http://localhost:8080/api/etraps/${id}`, {
//       method: 'PUT',
//       headers: { 'Content-Type': 'application/json' },
//       body: JSON.stringify({ etrap: editName }),
//     })
//       .then(res => res.json())
//       .then(updated => {
//         setEtraps(etraps.map(e => e.id === id ? updated : e));
//         setEditing(null);
//         setEditName('');
//       });
//   };

//   return (
//     <div>
//       <h1>Admin: Etraps</h1>

//       <input
//         placeholder="New Etrap"
//         value={newEtrap}
//         onChange={e => setNewEtrap(e.target.value)}
//       />
//       <button onClick={addEtrap}>Add</button>

//       <ul>
//         {etraps.map(etrap => (
//           <li key={etrap.id}>
//             {editing === etrap.id ? (
//               <>
//                 <input
//                   value={editName}
//                   onChange={e => setEditName(e.target.value)}
//                 />
//                 <button onClick={() => saveEdit(etrap.id)}>Save</button>
//                 <button onClick={() => setEditing(null)}>Cancel</button>
//               </>
//             ) : (
//               <>
//                 {etrap.etrap}
//                 <button onClick={() => startEdit(etrap)}>Edit</button>
//                 <button onClick={() => deleteEtrap(etrap.id)}>Delete</button>
//               </>
//             )}
//           </li>
//         ))}
//       </ul>
//     </div>
//   );
// }
