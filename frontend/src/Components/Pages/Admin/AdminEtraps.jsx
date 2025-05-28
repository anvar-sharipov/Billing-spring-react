import Input from "../../UI/Input";
import Button from "../../UI/Button";
import React, { useEffect, useState } from "react";
import Modal from "../../UI/Modal";
import UnorderedList from "../../UI/UnorderedList";

const AdminEtraps = () => {
  const [modalOn, setModalOn] = useState(false);
  const [etraps, setEtraps] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/etraps")
      .then((res) => res.json())
      .then((data) => setEtraps(data));
  }, []);
  return (
    <div>
      <Input placeholder="gg" />
      <Button variant="success" onClick={() => setModalOn(true)}>
        add
      </Button>
      <UnorderedList items={etraps} renderItem={(etrap) => etrap.etrap} />
      {modalOn && (
        <Modal onClose={() => setModalOn(false)}>
          <h2 className="text-xl font-semibold mb-4">Это модалка</h2>
          <p className="mb-4">Вы уверены?</p>
          <Button variant="danger">Да</Button>
        </Modal>
      )}
    </div>
  );
};

export default AdminEtraps;
