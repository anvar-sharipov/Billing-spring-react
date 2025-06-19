import { useState, useEffect, useRef } from "react";
import { Link } from "react-router-dom";

function Header() {
  const [dropdownOpen, setDropdownOpen] = useState<boolean>(false)

  const dropdownRef = useRef<HTMLDivElement>(null)

  useEffect(() => {
    function handleClickOutside(event: MouseEvent) {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
        setDropdownOpen(false);
      }
    }

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  return (
    <header className="bg-indigo-900 text-white py-4 px-8 shadow-lg flex items-center relative select-none">
      <div className="mr-6">
        <Link to={"/"}>
          <img
            src="/turkmentelecom-icon2.png"
            alt="Turkmentelecom Icon"
            className="w-25 md:w-38"
          />
        </Link>
      </div>

      <div ref={dropdownRef} className="relative">
        <button
          aria-haspopup="true"
          aria-expanded={dropdownOpen}
          onClick={() => setDropdownOpen(!dropdownOpen)}
          className="flex items-center gap-2 px-4 py-2 border-2 border-white rounded-md hover:border-indigo-300 transition-colors focus:outline-none"
        >
          Admin
          <svg
            className={`w-4 h-4 transform transition-transform duration-300 ${
              dropdownOpen ? "rotate-180" : ""
            }`}
            fill="none"
            stroke="currentColor"
            strokeWidth="2"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M19 9l-7 7-7-7"
            ></path>
          </svg>
        </button>

        <div
          role="menu"
          className={`absolute left-0 mt-2 w-48 bg-white text-gray-800 rounded-lg shadow-2xl origin-top scale-y-0 transform transition-transform duration-200 ${
            dropdownOpen ? "scale-y-100" : ""
          }`}
          style={{ transformOrigin: "top" }}
        >
          <Link
            to="/admin/models"
            className="block px-5 py-3 hover:bg-indigo-600 hover:text-white rounded-lg transition-colors"
            onClick={() => setDropdownOpen(false)}
          >
            Models
          </Link>
          {/* Можно добавить другие пункты */}
        </div>
      </div>
    </header>
  );
}

export default Header;
