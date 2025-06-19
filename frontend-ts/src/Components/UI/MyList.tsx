import React from "react";
import { motion, AnimatePresence } from "framer-motion";

type ListProps<T> = {
  items: T[];
  renderItem: (item: T) => React.ReactNode;
  getKey: (item: T) => React.Key;
  className?: string;
  emptyMessage?: string;
};

const itemVariants = {
  hidden: { opacity: 0, y: -10 },
  visible: { opacity: 1, y: 0, transition: { duration: .3 } },
  exit: { opacity: 0, y: 10, transition: { duration: 0.2 } },
};
function MyList<T>({
  items,
  renderItem,
  getKey,
  className = "",
  emptyMessage = "Список пуст",
}: ListProps<T>) {
  if (items.length === 0) {
    return (
      <div className="text-center py-6 text-gray-500 italic select-none">
        {emptyMessage}
      </div>
    );
  }
  return (
    <motion.ul
      className={`divide-y divide-gray-200 rounded-md border border-gray-300 shadow-sm ${className}`}
      initial="hidden"
      animate="visible"
      exit="exit"
    >
      <AnimatePresence>
        {items.map((item) => (
          <motion.li
            key={getKey(item)}
            variants={itemVariants}
            initial="hidden"
            animate="visible"
            exit="exit"
            layout
            className="px-4 py-3 hover:bg-gray-50 transition-colors cursor-pointer"
          >
            {renderItem(item)}
          </motion.li>
        ))}
      </AnimatePresence>
    </motion.ul>
  );
}
export default MyList;