import React, { useEffect, useState } from 'react'

const Modal = ({ onClose, children }) => {
  const [visible, setVisible] = useState(false)

  useEffect(() => {
    setVisible(true)
  }, [])

  const handleClose = () => {
    setVisible(false)
    setTimeout(() => onClose(), 300)
  }

  // Останавливаем всплытие клика внутри окна
  const handleContentClick = (e) => {
    e.stopPropagation()
  }
  return (
    <div
      className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50"
      onClick={handleClose} // Клик на фон — закрыть
    >
      <div
        className={`bg-white p-6 rounded-2xl shadow-xl w-full max-w-lg transform transition-all duration-300 ${
          visible ? 'scale-100 opacity-100' : 'scale-90 opacity-0'
        }`}
        onClick={handleContentClick} // Клик внутри окна — не закрывать
      >
        {children}
      </div>
    </div>
  )
}

export default Modal

// inset-0        = Задаёт значения top: 0; right: 0; bottom: 0; left: 0; одновременно.
//                  Элемент растягивается по всему экрану, занимает всю ширину и высоту родительского окна 
//                  (здесь — окна браузера, потому что fixed).
// bg-opacity-50  = Устанавливает прозрачность фона на 50% (0.5)
//                  Значит, чёрный фон будет полупрозрачным, давая эффект затемнения (overlay).
// shadow-xl      = Большая тень вокруг блока, чтобы придать глубину и "приподнятость".
// w-full         = Ширина 100% по родителю.
// max-w-md       = Максимальная ширина — размер md (по умолчанию примерно 28rem, т.е. 448px).
//                  Значит, элемент не будет шире 448px, даже если у родителя больше места.

// transform      = Включает поддержку CSS-трансформаций (нужно для анимации масштаба).
// transition-all = Плавный переход для всех анимируемых свойств (например, opacity, transform).
// duration-300   = Длительность анимации — 300 миллисекунд.

// 1. scale-100 и scale-90
//  Это классы для CSS-свойства transform: scale(...).
//  scale-100 означает масштаб 1, то есть без изменения размера (100%).
//  scale-90 означает масштаб 0.9, то есть элемент становится меньше на 10% по ширине и высоте.
// То есть:
//  При visible === true элемент будет в нормальном размере.
//  При visible === false элемент будет чуть уменьшен (плавное уменьшение).
// 2. opacity-100 и opacity-0
//  Классы для прозрачности (opacity).
//  opacity-100 — непрозрачность 100%, элемент полностью виден.
//  opacity-0 — прозрачность 0%, элемент полностью невидим.
// Для чего это нужно?
//  Чтобы анимировать появление и исчезновение модального окна.
//  Вместо резкого показа/скрытия, окно плавно увеличивается и становится видимым.
//  При скрытии — окно плавно уменьшается и исчезает.
// Пример CSS эквивалент:
// .element {
//   transform: scale(1);
//   opacity: 1;
//   transition: all 0.3s;
// }

// .element.hidden {
//   transform: scale(0.9);
//   opacity: 0;
// }



