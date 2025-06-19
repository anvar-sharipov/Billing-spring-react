import { motion } from "framer-motion";

const Divider = () => {
  return (
    <motion.hr
      initial={{ width: 0, opacity: 0 }}
      animate={{ width: "100%", opacity: 1 }}
      transition={{ duration: 1.8 }}
      className="border-t border-gray-300 my-6"
    />
  );
}

export default Divider