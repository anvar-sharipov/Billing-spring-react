import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import MyList from "../UI/MyList";

const AdminSidebarContent = () => {
  const [models, setModels] = useState<string[]>([]);

  useEffect(() => {
    fetch("/api/models")
      .then((res) => res.json())
      .then(setModels)
      .catch(console.error);
  }, []);

  const humanize = (name: string) =>
    name.replace(/_/g, " ").replace(/\b\w/g, (l) => l.toUpperCase());

  return (
    <MyList
      items={models}
      getKey={(model) => model}
      renderItem={(model) => (
        <Link
          to={`/admin/models/${model}`}
        >
          {humanize(model)}
        </Link>
      )}
    />
  );
};

export default AdminSidebarContent;
