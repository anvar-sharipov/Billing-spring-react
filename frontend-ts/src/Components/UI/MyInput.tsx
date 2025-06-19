import { motion } from "framer-motion";
import type { HTMLMotionProps } from "framer-motion";

type InputProps = HTMLMotionProps<"input">;

function MyInput({ className, ...props }: InputProps) {
  return (
    <motion.input
    initial={{ opacity: 0, y: 0 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 1.8 }}
      whileHover={{ scale: 1.05 }}
      whileTap={{ scale: 0.95 }}
      {...props}
      className="border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
    />
  
  );
}

export default MyInput;
