(ns webchange.ui-framework.components.icon.icon-slider)

(def data
  [:svg {:fill    "none"
         :viewBox "0 0 14 14"
         :height  "14"
         :width   "14"}
  [:path {:fill "black"
          :d    "M12.3333 4.45334V1.00001C12.3333 0.823199 12.2631 0.65363 12.1381 0.528606C12.013 0.403581 11.8435 0.333344 11.6666 0.333344C11.4898 0.333344 11.3203 0.403581 11.1952 0.528606C11.0702 0.65363 11 0.823199 11 1.00001V4.45334C10.6139 4.59348 10.2803 4.8491 10.0445 5.18545C9.8088 5.52181 9.68233 5.9226 9.68233 6.33334C9.68233 6.74409 9.8088 7.14488 10.0445 7.48123C10.2803 7.81759 10.6139 8.07321 11 8.21334V13C11 13.1768 11.0702 13.3464 11.1952 13.4714C11.3203 13.5964 11.4898 13.6667 11.6666 13.6667C11.8435 13.6667 12.013 13.5964 12.1381 13.4714C12.2631 13.3464 12.3333 13.1768 12.3333 13V8.21334C12.7194 8.07321 13.053 7.81759 13.2888 7.48123C13.5245 7.14488 13.651 6.74409 13.651 6.33334C13.651 5.9226 13.5245 5.52181 13.2888 5.18545C13.053 4.8491 12.7194 4.59348 12.3333 4.45334V4.45334ZM11.6666 7.00001C11.5348 7.00001 11.4059 6.96091 11.2963 6.88766C11.1866 6.8144 11.1012 6.71028 11.0507 6.58847C11.0003 6.46665 10.9871 6.3326 11.0128 6.20328C11.0385 6.07396 11.102 5.95517 11.1952 5.86194C11.2885 5.7687 11.4073 5.70521 11.5366 5.67949C11.6659 5.65376 11.8 5.66697 11.9218 5.71742C12.0436 5.76788 12.1477 5.85333 12.221 5.96296C12.2942 6.0726 12.3333 6.20149 12.3333 6.33334C12.3333 6.51015 12.2631 6.67972 12.1381 6.80475C12.013 6.92977 11.8435 7.00001 11.6666 7.00001ZM7.66665 8.45334V1.00001C7.66665 0.823199 7.59641 0.65363 7.47139 0.528606C7.34636 0.403581 7.17679 0.333344 6.99998 0.333344C6.82317 0.333344 6.6536 0.403581 6.52858 0.528606C6.40355 0.65363 6.33332 0.823199 6.33332 1.00001V8.45334C5.94721 8.59348 5.61362 8.8491 5.37788 9.18545C5.14213 9.52181 5.01567 9.9226 5.01567 10.3333C5.01567 10.7441 5.14213 11.1449 5.37788 11.4812C5.61362 11.8176 5.94721 12.0732 6.33332 12.2133V13C6.33332 13.1768 6.40355 13.3464 6.52858 13.4714C6.6536 13.5964 6.82317 13.6667 6.99998 13.6667C7.17679 13.6667 7.34636 13.5964 7.47139 13.4714C7.59641 13.3464 7.66665 13.1768 7.66665 13V12.2133C8.05275 12.0732 8.38634 11.8176 8.62209 11.4812C8.85783 11.1449 8.9843 10.7441 8.9843 10.3333C8.9843 9.9226 8.85783 9.52181 8.62209 9.18545C8.38634 8.8491 8.05275 8.59348 7.66665 8.45334ZM6.99998 11C6.86813 11 6.73924 10.9609 6.6296 10.8877C6.51997 10.8144 6.43452 10.7103 6.38406 10.5885C6.3336 10.4666 6.3204 10.3326 6.34613 10.2033C6.37185 10.074 6.43534 9.95517 6.52858 9.86194C6.62181 9.7687 6.7406 9.70521 6.86992 9.67949C6.99924 9.65376 7.13329 9.66697 7.25511 9.71742C7.37692 9.76788 7.48104 9.85333 7.5543 9.96296C7.62755 10.0726 7.66665 10.2015 7.66665 10.3333C7.66665 10.5102 7.59641 10.6797 7.47139 10.8047C7.34636 10.9298 7.17679 11 6.99998 11ZM2.99998 3.12001V1.00001C2.99998 0.823199 2.92975 0.65363 2.80472 0.528606C2.6797 0.403581 2.51013 0.333344 2.33332 0.333344C2.15651 0.333344 1.98694 0.403581 1.86191 0.528606C1.73689 0.65363 1.66665 0.823199 1.66665 1.00001V3.12001C1.28055 3.26015 0.946957 3.51576 0.711211 3.85212C0.475465 4.18848 0.348999 4.58926 0.348999 5.00001C0.348999 5.41076 0.475465 5.81154 0.711211 6.1479C0.946957 6.48426 1.28055 6.73987 1.66665 6.88001V13C1.66665 13.1768 1.73689 13.3464 1.86191 13.4714C1.98694 13.5964 2.15651 13.6667 2.33332 13.6667C2.51013 13.6667 2.6797 13.5964 2.80472 13.4714C2.92975 13.3464 2.99998 13.1768 2.99998 13V6.88001C3.38608 6.73987 3.71968 6.48426 3.95542 6.1479C4.19117 5.81154 4.31763 5.41076 4.31763 5.00001C4.31763 4.58926 4.19117 4.18848 3.95542 3.85212C3.71968 3.51576 3.38608 3.26015 2.99998 3.12001V3.12001ZM2.33332 5.66668C2.20146 5.66668 2.07257 5.62758 1.96294 5.55432C1.8533 5.48107 1.76786 5.37695 1.7174 5.25513C1.66694 5.13332 1.65374 4.99927 1.67946 4.86995C1.70518 4.74063 1.76868 4.62184 1.86191 4.52861C1.95515 4.43537 2.07394 4.37188 2.20326 4.34615C2.33258 4.32043 2.46662 4.33363 2.58844 4.38409C2.71026 4.43455 2.81438 4.52 2.88763 4.62963C2.96088 4.73926 2.99998 4.86816 2.99998 5.00001C2.99998 5.17682 2.92975 5.34639 2.80472 5.47141C2.6797 5.59644 2.51013 5.66668 2.33332 5.66668Z"}]])