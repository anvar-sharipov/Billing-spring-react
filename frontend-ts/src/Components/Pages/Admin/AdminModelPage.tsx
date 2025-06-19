import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import MyList from "../../UI/MyList";
import MyInput from "../../UI/MyInput";
import Button from "../../UI/Button";
import Divider from "../../UI/Divider";

const AdminModelPage = () => {
  const { modelName } = useParams();
  const [items, setItems] = useState<any[]>([]);
  const [newData, setNewData] = useState("");

  useEffect(() => {
    fetch(`/api/admin/models/${modelName}`)
      .then((res) => res.json())
      .then(setItems)
      .catch(console.error);
  }, [modelName]);

  return (
    <div>
      <h2 className="text-xl font-bold mb-4 capitalize">{modelName?.replace(/_/g, " ")}</h2>

      <div className="flex gap-4">
        <MyInput
          placeholder={`Add new ${modelName}`}
          type="text"
          value={newData}
          onChange={(e) => setNewData(e.target.value)}
        />
        <Button>add</Button>
      </div>

      <Divider />

      <MyList
        items={items}
        getKey={(item) => item.id}
        renderItem={(item) => (
          <div className="text-gray-800">
            {Object.entries(item).map(([k, v]) => (
              <span key={k} className="mr-4">
                <strong>{k}:</strong> {v?.toString()}
              </span>
            ))}
          </div>
        )}
        emptyMessage={`Нет данных для ${modelName}`}
      />
    </div>
  );
};

export default AdminModelPage;
