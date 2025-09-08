import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

test('nothing yet in GUI', () => {
  const { getByText } = render(<App />);

  //const linkElement = getByText(/learn react/i);
  //expect(linkElement).toBeInTheDocument();
});
