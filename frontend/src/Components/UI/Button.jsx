import React from "react";

const variantClasses = {
  primary: "bg-blue-600 hover:bg-blue-700",
  success: "bg-green-500 hover:bg-green-600",
  danger: "bg-red-500 hover:bg-red-600",
  warning: "bg-yellow-400 hover:bg-yellow-500 text-white",
  disabled: "bg-gray-400 cursor-not-allowed",
};

const Button = ({children, disabled, variant = 'primary', ...props}) => {
    const baseClasses = "px-4 py-2 text-white font-semibold transition";
    const variantClass = disabled ? variantClasses.disabled : variantClasses[variant] || variantClasses.primary;

  return (
    <button class={`${baseClasses} ${variantClass}`} {...props}>
      {children}
    </button>
  );
};

export default Button;
