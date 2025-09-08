import { Coordinate, Model } from './Model'

describe('Coordinate', () => {

  // simplest test case
  test('initialize', () => {
    let coord = new Coordinate(2, 3)
    expect(coord.row).toBe(2)
    expect(coord.column).toBe(3)
  })


})