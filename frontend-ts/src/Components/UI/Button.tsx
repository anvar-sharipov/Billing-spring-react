import React from "react";
import { motion } from "framer-motion";

const variantClasses = {
  primary: "bg-blue-600 hover:bg-blue-700",
  success: "bg-green-500 hover:bg-green-600",
  danger: "bg-red-500 hover:bg-red-600",
  warning: "bg-yellow-400 hover:bg-yellow-500 text-white",
  disabled: "bg-gray-400 cursor-not-allowed",
};

type Variant = keyof typeof variantClasses;
interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: Variant;
  disabled?: boolean;
  children: React.ReactNode; // явно указать children, если хочешь
}

const Button: React.FC<ButtonProps> = ({
  children,
  disabled = false,
  variant = "primary",
  ...props
}: ButtonProps) => {
  const baseClasses = "px-4 py-2 text-white font-semibold transition rounded-lg";
  const variantClass = disabled
    ? variantClasses.disabled
    : variantClasses[variant] || variantClasses.primary;

  return (
    <motion.div
      initial={{ opacity: 0, y: 0 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 1.8 }}
      whileHover={{ scale: 1.05 }}
      whileTap={{ scale: 0.95 }}
    >
      <button
        className={`${baseClasses} ${variantClass}`}
        disabled={disabled}
        {...props}
      >
        {children}
      </button>
    </motion.div>
  );
};

export default Button;
